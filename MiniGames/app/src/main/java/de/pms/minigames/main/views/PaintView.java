package de.pms.minigames.main.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;
import android.widget.Toast;


/**
 * Creates a PaintView to draw something on the screen
 */
public class PaintView extends View {

    private Paint paintSelected;
    private Path path;
    private Canvas canvas;
    private Bitmap canvasBitmap;
    private float strokeWidth;
    private int screenWidth, screenHeight;

    /**
     * Constructor of the PaintView.
     * Sets default colour to black.
     *
     * @param context Gives the context to the View.
     * @param attrs   Gives attributes to the View.
     */
    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        path = new Path();
        strokeWidth = 3f;
        paintSelected = createColor(Color.BLACK, strokeWidth);
    }

    /**
     * Adapts the size of the screen.
     *
     * @param w    Current width
     * @param h    Current height
     * @param oldw Old width
     * @param oldh Old height
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasBitmap.eraseColor(Color.WHITE);
        canvas = new Canvas(canvasBitmap);
    }

    /**
     * Draws something on the canvas.
     *
     * @param canvas Gives the current canvas
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, paintSelected);
        canvas.drawPath(path, paintSelected);
        this.canvas = new Canvas(canvasBitmap);

    }

    /**
     * Calculates the coordinates if something gets drawn.
     *
     * @param motionEvent Gives a motion event
     * @return Returns a boolean flag if the user draws something.
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float xCoor = motionEvent.getX();
        float yCoor = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xCoor, yCoor);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xCoor, yCoor);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paintSelected);
                path.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    /**
     * Changes the colour of the brush.
     *
     * @param colorState Saves the colour state. To recognize which colour is selected.
     */
    public void setColor(int colorState) {
        switch (colorState) {
            case 1:
                Toast.makeText(getContext(), "schwarz", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.BLACK, strokeWidth);
                break;
            case 2:
                Toast.makeText(getContext(), "rot", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.RED, strokeWidth);
                break;
            case 3:
                Toast.makeText(getContext(), "gelb", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.YELLOW, strokeWidth);
                break;
            case 4:
                Toast.makeText(getContext(), "blau", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.BLUE, strokeWidth);
                break;
            case 5:
                Toast.makeText(getContext(), "grün", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.GREEN, strokeWidth);
                break;
            case 6:
                Toast.makeText(getContext(), "löschen", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.WHITE, strokeWidth);
                break;
            case 7:
                Toast.makeText(getContext(), "braun", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.rgb(139, 90, 43), strokeWidth);
                break;
            case 8:
                Toast.makeText(getContext(), "lila", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.rgb(136, 0, 255), strokeWidth);
                break;
        }
    }

    /**
     * Changes the brush size.
     *
     * @param sizeState Gives state what size to use.
     */
    public void setBrushSize(int sizeState) {
        switch (sizeState) {
            case 1:
                Toast.makeText(getContext(), "klein", Toast.LENGTH_SHORT).show();
                strokeWidth = 3f;
                paintSelected = createColor(paintSelected.getColor(), strokeWidth);
                break;
            case 2:
                Toast.makeText(getContext(), "medium", Toast.LENGTH_SHORT).show();
                strokeWidth = 6f;
                paintSelected = createColor(paintSelected.getColor(), strokeWidth);
                break;
            case 3:
                Toast.makeText(getContext(), "groß", Toast.LENGTH_SHORT).show();
                strokeWidth = 10f;
                paintSelected = createColor(paintSelected.getColor(), strokeWidth);
                break;
        }
    }

    /**
     * Gives the current bitmap.
     *
     * @return Returns the current bitmap.
     */
    public Bitmap saveImage() {
        return canvasBitmap;
    }

    /**
     * Sets a colour to the paint brush.
     *
     * @param color Contains the colour of the new brush.
     * @return Returns the colour as Paint object.
     */
    private Paint createColor(int color, float strokeWidth) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(strokeWidth);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(color);

        return p;
    }

    /**
     * Resets the View to delete all drawings.
     */
    public void clear() {
        canvasBitmap.eraseColor(Color.WHITE);
    }

    /**
     * Resets the View and inserts Outline Pictures.
     *
     * @param res Id of the Outline Picture in resources.
     */
    public void clear(int res) {
        clear();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;

        canvasBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), res, options), screenWidth, screenHeight, false);
        invalidate();
    }
}
