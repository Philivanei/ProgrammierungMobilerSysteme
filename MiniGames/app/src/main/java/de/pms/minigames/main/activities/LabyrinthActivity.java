package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;

import android.os.Bundle;

public class LabyrinthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinth);

        //adds back button and activity title
        getSupportActionBar().setTitle("Labyrinth");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
