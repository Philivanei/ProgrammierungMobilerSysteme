package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;
import de.pms.minigames.main.views.PaintView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaintActivity extends AppCompatActivity {

    int count = 0;
    private PaintView paintView;
    private Button buttonReset, buttonBlack, buttonYellow,
            buttonRed, buttonGreen, buttonBlue, buttonErase, buttonOutline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);
        paintView = (PaintView) findViewById(R.id.paint_view);
        buttonReset = (Button) findViewById(R.id.button_reset);
        buttonBlack = (Button)  findViewById(R.id.black);
        buttonRed = (Button)  findViewById(R.id.red);
        buttonYellow = (Button)  findViewById(R.id.yellow);
        buttonBlue = (Button)  findViewById(R.id.blue);
        buttonGreen = (Button)  findViewById(R.id.green);
        buttonErase = (Button)  findViewById(R.id.erase);
        buttonOutline = (Button) findViewById(R.id.outline);


        //adds back button and activity title
        getSupportActionBar().setTitle("Malen");
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
                if(count > 5){
                    count = 0;
                }
                switch(count){
                    case 0:
                        paintView.setBackgroundResource(R.drawable.appleoutline);
                        count ++;
                        break;
                    case 1:
                        paintView.setBackgroundResource(R.drawable.butterflyoutline);
                        count ++;
                        break;
                    case 2:
                        paintView.setBackgroundResource(R.drawable.fishoutline);
                        count ++;
                        break;
                    case 3:
                        paintView.setBackgroundResource(R.drawable.housoutline);
                        count ++;
                        break;
                    case 4:
                        paintView.setBackgroundResource(R.drawable.staroutline);
                        count ++;
                        break;
                    case 5:
                        paintView.setBackgroundColor(Color.TRANSPARENT);
                        count ++;
                        break;
                }
            }
        });

    }
}
