package de.pms.minigames.main.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import de.pms.minigames.main.helper.Cell;

/**
 * Creates a LabyrinthView to show the activity of the accelerometer.
 */
public class LabyrinthView extends View {
    private Paint paint = new Paint();
    private boolean screenSizeCalled = false;
    private float xCor, yCor;
    private int xCount = 0, yCount = 0;
    private int screenWidth;
    private int screenHeight;
    private Cell[][] cells;
    private final int COLUMNS = 5, ROWS = 10;

    /**
     * Constructor of the LabyrinthView.
     * Sets default values to the drawn circle.
     *
     * @param context Gives the context to the View.
     * @param attrs   Gives attributes to the View.
     */
    public LabyrinthView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
     * Creates a labyrinth in 2D array with information of walls
     */
    private void createLabyrinth() {

        cells = new Cell[COLUMNS][ROWS];

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                //Creates the amount of cells that are defined with COLUMNS and ROWS
                cells[x][y] = new Cell(x, y);

                // Creates the walls with a random boolean -> random wall generating
                Random random = new Random();
                cells[x][y].setLeft(random.nextBoolean());
                cells[x][y].setRight(random.nextBoolean());

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
            }
        }
    }

    /**
     * The function moveCircle changes the circle coordinates. It also limits the circle to the screen
     * size that the circle-coordinates can't get out of the screen.
     *
     * @param dx New value of the x-coordinate.
     * @param dy New value of the y-coordinate.
     */
    public void moveCircle(int dx, int dy) {

        //rechts
        if (dx > 0 && !cells[xCount][yCount].getRight()) {
            xCor = (float) (Math.ceil(xCor) + getCellSize());
            xCount++;
            //Thread.sleep(1000);
        }
        //links
        if (dx < 0 && !cells[xCount][yCount].getLeft()) {
            xCor = (float) (Math.ceil(xCor) - getCellSize());
            xCount--;
            //Thread.sleep(1000);
        }
        //nach unten
        if (dy > 0 && !cells[xCount][yCount].getBottom()) {
            yCor = (float) (Math.ceil(yCor) + getCellSize());
            yCount++;
            //Thread.sleep(1000);
        }
        //nach oben
        if (dy < 0 && !cells[xCount][yCount].getTop()) {
            yCor = (float) (Math.ceil(yCor) - getCellSize());
            yCount--;
            //Thread.sleep(1000);
        }
        invalidate();

        //Limits the circle to the bounding of the COLUMNS (max x)
        //ist noch nötig weil aus irgendwelchen gründen der kreis sich ein bisschen verschiebt
        //der reset an einer wand hält ihn dann "mittig"
        if (screenSizeCalled) {
            if (xCor > (((COLUMNS - 1) * getCellSize()) + getCellSize() / 2)) {
                xCor = (((COLUMNS - 1) * getCellSize()) + getCellSize() / 2);
                xCount = COLUMNS - 1;
            }
        }
        if (xCor < 0) {
            xCor = 0 + getCellSize() / 2;
            xCount = 0;
        }
        //Limits the circle to the bounding of the ROWS (max y)
        if (screenSizeCalled) {
            if (yCor > (((ROWS - 1) * getCellSize()) + getCellSize() / 2)) {
                yCor = (((ROWS - 1) * getCellSize()) + getCellSize() / 2);
                yCount = ROWS - 1;
            }
        }
        if (yCor < 0) {
            yCor = 0 + getCellSize() / 2;
            yCount = 0;
        }
    }

    /**
     * Gets the current screen size.
     *
     * @param w    Current screen width.
     * @param h    Current screen height.
     * @param oldw Last screen width.
     * @param oldh Last screen height.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenSizeCalled = true;
        screenWidth = w;
        screenHeight = h;
        xCor = getCellSize() / 2;
        yCor = getCellSize() / 2;
    }

    /**
     * Draws a canvas to the screen.
     *
     * @param canvas Canvas of the function.
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //Sets the origin to the desired spacing
        canvas.translate(getHorizontalMargin(), getVerticalMargin());
        //Draws the labyrinth
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
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

        //Draws the movement of the circle
        canvas.drawCircle(xCor, yCor, getCellSize() / 3, paint);
    }
}