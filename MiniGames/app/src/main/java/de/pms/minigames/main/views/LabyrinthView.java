package de.pms.minigames.main.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Creates a LabyrinthView to show the activity of the accelerometer.
 */
public class LabyrinthView extends View {
    private Paint paint = new Paint();
    private boolean screenSizeCalled = false;
    private float xCor;
    private float yCor;
    private int screenWidth;
    private int screenHeight;

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

        xCor = 300;
        yCor = 300;
    }

    /**
     * The function move changes the circle coordinates. It also limits the circle to the screen
     * size that the circle-coordinates can't get out of the screen.
     *
     * @param dx New value of the x-coordinate.
     * @param dy New value of the y-coordinate.
     */
    public void move(int dx, int dy) {
        xCor = xCor + dx;
        yCor = yCor + dy;
        invalidate();

        if (screenSizeCalled) {
            if (xCor > screenWidth) {
                xCor = screenWidth;
            }
        }
        if (xCor < 0) {
            xCor = 0;
        }
        if (screenSizeCalled) {
            if (yCor > screenHeight) {
                yCor = screenHeight;
            }
        }
        if (yCor < 0) {
            yCor = 0;
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
    }

    /**
     * Draws a canvas to the screen.
     *
     * @param canvas Gives a canvas to the function.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(xCor, yCor, 20, paint);
        canvas.drawLine(50, 50, 300, 300, paint);
    }
}
