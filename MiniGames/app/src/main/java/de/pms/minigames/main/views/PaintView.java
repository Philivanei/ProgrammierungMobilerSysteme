package de.pms.minigames.main.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;
import android.widget.Toast;


public class PaintView extends View {

    private Paint paintSelected;
    private Path path;
    private Canvas canvas;
    private Bitmap canvasBitmap;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        path = new Path();
        paintSelected = createColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, paintSelected);
        canvas.drawPath(path, paintSelected);
        this.canvas = new Canvas(canvasBitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float xCoor = motionEvent.getX();
        float yCoor = motionEvent.getY();

        switch (motionEvent.getAction()){
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

    public void setColor(int colorState){

        switch(colorState){
            case 1:
                Toast.makeText(getContext(), "schwarz", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.BLACK);
                break;
            case 2:
                Toast.makeText(getContext(), "rot", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.RED);
                break;
            case 3:
                Toast.makeText(getContext(), "gelb", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.YELLOW);
                break;
            case 4:
                Toast.makeText(getContext(), "blau", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.BLUE);
                break;
            case 5:
                Toast.makeText(getContext(), "grün", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.GREEN);
                break;
            case 6:
                Toast.makeText(getContext(), "löschen", Toast.LENGTH_SHORT).show();
                paintSelected = createColor(Color.WHITE);
                break;
        }
    }

    private Paint createColor(int color){
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(3f);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(color);

        return p;
    }

    public void clear(){
        canvasBitmap.eraseColor(Color.WHITE);
    }

}
