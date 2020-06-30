package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The MainActivity can call the other activities.
 */
public class MainActivity extends AppCompatActivity {

    private Button buttonLabyrinth;
    private Button buttonPaint;

    /**
     * The state of the activity can be saved.
     * Elements like buttons get initialized.
     *
     * @param savedInstanceState Saves the state.
     */
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
