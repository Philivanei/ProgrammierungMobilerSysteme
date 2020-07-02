package de.pms.minigames.main.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import de.pms.minigames.main.helper.Cell;

/**
 * Creates a LabyrinthView to show the activity of the accelerometer and the Labyrinth.
 */
public class LabyrinthView extends View {
    private static final long TIME_BETWEEN_UPDATES = 300;
    private static final float HOLE_PUNCH_PROBABILITY = 0.2f;
    private Paint paint = new Paint();
    private Paint goalPaint = new Paint();
    private boolean screenSizeCalled = false, isGoalReached = false;
    private float xCor, yCor;
    private int xCount = 0, yCount = 0;
    private int screenWidth, screenHeight;
    private Cell[][] cells;
    private final int COLUMNS = 5, ROWS = 10;
    private long lastUpdateOn = System.currentTimeMillis();

    /**
     * Constructor of the LabyrinthView.
     * Sets default values to the drawn circle.
     *
     * @param context Gives the context to the View.
     * @param attrs   Gives attributes to the View.
     */
    public LabyrinthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        goalPaint.setAntiAlias(true);
        goalPaint.setStrokeWidth(10f);
        goalPaint.setColor(Color.RED);
        goalPaint.setStyle(Paint.Style.STROKE);
        goalPaint.setStrokeJoin(Paint.Join.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        createLabyrinth();
    }

    /**
     * Returns the size of a cell.
     *
     * @return Size of the cell.
     */
    public float getCellSize() {
        if (screenWidth / screenHeight < COLUMNS / ROWS) {
            return screenWidth / (COLUMNS + 1f);
        } else {
            return screenHeight / (ROWS + 1f);
        }
    }

    /**
     * Returns the horizontal margin.
     *
     * @return Horizontal Margin.
     */
    public float getHorizontalMargin() {
        return (screenWidth - COLUMNS * getCellSize()) / 2;
    }

    /**
     * Returns the vertical margin.
     *
     * @return vertical margin.
     */
    public float getVerticalMargin() {
        return (screenHeight - ROWS * getCellSize()) / 2;
    }

    /**
     * Creates a path in case that there is a wall without a hole.
     */
    private void makePath() {
        //-1 because the last row must not have an opening
        for (int x = 0; x < cells.length - 1; x++) {
            boolean hasPath = false;
            for (int y = 0; y < cells[0].length; y++) {
                hasPath |= !cells[x][y].getRight();
            }
            if (!hasPath) {
                int pathCellY = (int) Math.floor(Math.random() * cells[0].length);
                cells[x][pathCellY].setRight(false);
                cells[x + 1][pathCellY].setLeft(false);
            }

        }
    }

    /**
     * Creates a labyrinth in 2D array with information of walls
     */
    private void createLabyrinth() {

        cells = new Cell[COLUMNS][ROWS];

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                cells[x][y] = new Cell();
            }
        }
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {

                boolean doHolePunch = Math.random() < HOLE_PUNCH_PROBABILITY;

                if (x == 0) {
                    cells[x][y].setLeft(true);
                }
                if (x == COLUMNS - 1) {
                    cells[x][y].setRight(true);
                }
                if (y == 0) {
                    cells[x][y].setTop(true);
                } else {
                    cells[x][y].setTop(false);
                }
                if (y == ROWS - 1) {
                    cells[x][y].setBottom(true);
                } else {
                    cells[x][y].setBottom(false);
                }

                //We don't want to have holes in the right wall so we make sure the hole-punch
                // ignores the last one
                if (doHolePunch && x < cells.length - 1) {
                    cells[x][y].setRight(false);
                    cells[x + 1][y].setLeft(false);
                }

            }
        }

        makePath();
    }

    /**
     * The function moveCircle changes the circle coordinates. It also limits the circle to the
     * screen-size that the circle-coordinates can't get out of the screen. If the circle gets to
     * the goal a toast will appear.
     *
     * @param dx New value of the x-coordinate.
     * @param dy New value of the y-coordinate.
     */
    public void moveCircle(int dx, int dy) {
        if (screenSizeCalled && System.currentTimeMillis() - lastUpdateOn > TIME_BETWEEN_UPDATES) {
            lastUpdateOn = System.currentTimeMillis();
            if (!isGoalReached) {
                //One step right
                if (dx > 0 && !cells[xCount][yCount].getRight()) {
                    xCor = (float) (Math.ceil(xCor) + getCellSize());
                    xCount++;
                }
                //One step left
                if (dx < 0 && !cells[xCount][yCount].getLeft()) {
                    xCor = (float) (Math.ceil(xCor) - getCellSize());
                    xCount--;
                }
                //One step down
                if (dy > 0 && !cells[xCount][yCount].getBottom()) {
                    yCor = (float) (Math.ceil(yCor) + getCellSize());
                    yCount++;
                }
                //One step up
                if (dy < 0 && !cells[xCount][yCount].getTop()) {
                    yCor = (float) (Math.ceil(yCor) - getCellSize());
                    yCount--;
                }
                invalidate();

                //Limits the circle to the bounding of the COLUMNS (max x)
                if (xCor > (((COLUMNS - 1) * getCellSize()) + getCellSize() / 2)) {
                    xCor = (((COLUMNS - 1) * getCellSize()) + getCellSize() / 2);
                    xCount = COLUMNS - 1;

                }
                if (xCor < 0) {
                    xCor = getCellSize() / 2;
                    xCount = 0;
                }
                //Limits the circle to the bounding of the ROWS (max y)
                if (yCor > (((ROWS - 1) * getCellSize()) + getCellSize() / 2)) {
                    yCor = (((ROWS - 1) * getCellSize()) + getCellSize() / 2);
                    yCount = ROWS - 1;
                }
                if (yCor < 0) {
                    yCor = getCellSize() / 2;
                    yCount = 0;
                }

                //Sets goal to the right bottom
                if ((xCor == (((COLUMNS - 1) * getCellSize()) + getCellSize() / 2)) &&
                        (yCor == (((ROWS - 1) * getCellSize()) + getCellSize() / 2))) {
                    Toast.makeText(getContext(), "Finished!", Toast.LENGTH_LONG).show();
                    isGoalReached = true;
                }
            }
        }
    }

    /**
     * Gets the current screen size and sets the start coordinates of the player-circle.
     *
     * @param w    Current screen width.
     * @param h    Current screen height.
     * @param oldw Last screen width.
     * @param oldh Last screen height.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenSizeCalled = w > 0 && h > 0;
        screenWidth = w;
        screenHeight = h;
        xCor = getCellSize() / 2;
        yCor = getCellSize() / 2;
    }

    /**
     * Draws the player-circle movement, the labyrinth and the finish-circle.
     *
     * @param canvas Canvas of the function.
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //Sets the origin to the desired spacing
        canvas.translate(getHorizontalMargin(), getVerticalMargin());
        //Draws the labyrinth
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                if (cells[x][y].getTop()) {
                    canvas.drawLine(x * getCellSize(), y * getCellSize(),
                            (x + 1) * getCellSize(), y * getCellSize(), paint);
                }
                if (cells[x][y].getBottom()) {
                    canvas.drawLine(x * getCellSize(), (y + 1) * getCellSize(),
                            (x + 1) * getCellSize(), (y + 1) * getCellSize(), paint);
                }
                if (cells[x][y].getLeft()) {
                    canvas.drawLine(x * getCellSize(), y * getCellSize(),
                            x * getCellSize(), (y + 1) * getCellSize(), paint);
                }
                if (cells[x][y].getRight()) {
                    canvas.drawLine((x + 1) * getCellSize(), y * getCellSize(),
                            (x + 1) * getCellSize(), (y + 1) * getCellSize(), paint);
                }
            }
        }

        //Draws the goal
        canvas.drawCircle((((COLUMNS - 1) * getCellSize()) + getCellSize() / 2),
                (((ROWS - 1) * getCellSize()) + getCellSize() / 2), getCellSize() / 4, goalPaint);
        //Draws the movement of the player-circle
        canvas.drawCircle(xCor, yCor, getCellSize() / 3, paint);
    }
}