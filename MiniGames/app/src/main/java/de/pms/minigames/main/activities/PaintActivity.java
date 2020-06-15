package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;
import de.pms.minigames.main.views.PaintView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaintActivity extends AppCompatActivity {

    private PaintView paintView;
    private Button buttonReset;
    private Button menue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);
        paintView = (PaintView) findViewById(R.id.paint_view);
        buttonReset = (Button) findViewById(R.id.button_reset);
        menue = (Button) findViewById(R.id.main_menue_paint);


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });

        menue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
