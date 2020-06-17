package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;
import de.pms.minigames.main.views.PaintView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The PaintActivity manages the Activities of the paint screen and initializes a PaintView
 */
public class PaintActivity extends AppCompatActivity {

    private int count = 0;
    private PaintView paintView;
    private Button buttonReset, buttonBlack, buttonYellow,
            buttonRed, buttonGreen, buttonBlue, buttonErase, buttonOutline;

    /**
     * The state of the activity can be saved.
     * Necessary elements like buttons or the PaintView get initialized.
     *
     * @param savedInstanceState saves the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);
        paintView = (PaintView) findViewById(R.id.paint_view);
        buttonReset = (Button) findViewById(R.id.button_reset);
        buttonBlack = (Button) findViewById(R.id.black);
        buttonRed = (Button) findViewById(R.id.red);
        buttonYellow = (Button) findViewById(R.id.yellow);
        buttonBlue = (Button) findViewById(R.id.blue);
        buttonGreen = (Button) findViewById(R.id.green);
        buttonErase = (Button) findViewById(R.id.erase);
        buttonOutline = (Button) findViewById(R.id.outline);

        //adds back button and activity title
        getSupportActionBar().setTitle("Paint");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });
        buttonBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(1);
            }
        });
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(2);
            }
        });
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(3);
            }
        });
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(4);
            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(5);
            }
        });
        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(6);
            }
        });

        buttonOutline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDrawable();
            }
        });

    }

    /**
     * setDrawable can be called by a button click to put outline examples into the background
     * of the paintView.
     */
    private void setDrawable() {
        if (count > 5) {
            count = 0;
        }
        count++;
        switch (count) {
            case 1:
                paintView.setBackgroundResource(R.drawable.appleoutline);
                break;
            case 2:
                paintView.setBackgroundResource(R.drawable.butterflyoutline);
                break;
            case 3:
                paintView.setBackgroundResource(R.drawable.fishoutline);
                break;
            case 4:
                paintView.setBackgroundResource(R.drawable.housoutline);
                break;
            case 5:
                paintView.setBackgroundResource(R.drawable.staroutline);
                break;
            case 6:
                paintView.setBackgroundColor(Color.WHITE);
                break;
        }
    }
}
