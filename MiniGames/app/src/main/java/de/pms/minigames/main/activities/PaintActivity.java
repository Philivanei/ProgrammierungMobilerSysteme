package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;
import de.pms.minigames.main.views.PaintView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaintActivity extends AppCompatActivity {

    private PaintView paintView;
    private Button buttonReset, buttonBlack, buttonYellow,
            buttonRed, buttonGreen, buttonBlue, buttonErase;

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

    }
}
