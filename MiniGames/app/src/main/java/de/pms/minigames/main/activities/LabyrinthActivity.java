package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.pms.minigames.R;
import de.pms.minigames.main.views.LabyrinthView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.util.Objects;

/**
 * The LabyrinthActivity manages the Activities of the labyrinth screen and initializes a
 * LabyrinthView.
 */
public class LabyrinthActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private LabyrinthView labyrinthView;

    /**
     * The state of the activity can be saved.
     * Necessary elements like the LabyrinthView get initialized.
     *
     * @param savedInstanceState Saves the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinth);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        labyrinthView = findViewById(R.id.labyrinth_view);

        //adds back button and activity title
        Objects.requireNonNull(getSupportActionBar()).setTitle("Labyrinth");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     * Detects if the accelerometer has changed its values.
     * @param event Gives the event of the accelerometer.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            labyrinthView.move((int) -values[0], (int) values[1]);
        }
    }

    /**
     * Detects if the accuracy of the accelerometer has changed.
     * @param sensor Gives values of the sensor and capabilities.
     * @param accuracy Gives the accuracy as an integer value.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
