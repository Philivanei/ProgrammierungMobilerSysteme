package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonLabyrinth;
    private Button buttonPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLabyrinth = (Button)findViewById(R.id.button_labyrinth);
        buttonPaint = (Button)findViewById(R.id.button_paint);

        buttonLabyrinth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LabyrinthActivity.class);
                startActivity(intent);
            }
        });

        buttonPaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaintActivity.class);
                startActivity(intent);
            }
        });
    }

}
