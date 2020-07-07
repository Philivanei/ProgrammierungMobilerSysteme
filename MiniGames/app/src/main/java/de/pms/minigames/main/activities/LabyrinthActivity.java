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
 * The LabyrinthActivity manages the activities (incl. accelerometer) of the labyrinth-screen and
 * initializes a LabyrinthView.
 */
public class LabyrinthActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private LabyrinthView labyrinthView;

    /**
     * The state of the activity can be saved.
     * Necessary elements like the LabyrinthView or the sensor-manager get initialized.
     *
     * @param savedInstanceState Saves the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinth);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        labyrinthView = findViewById(R.id.labyrinth_view);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Labyrinth");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * Detects if the accelerometer has changed its values. Depending on the values the moveCircle
     * function in LabyrinthView gets called.
     *
     * @param event Event of the accelerometer.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            labyrinthView.moveCircle((int) -values[0], (int) values[1]);
        }
    }

    /**
     * Detects if the accuracy of the accelerometer has changed.
     *
     * @param sensor   Values of the sensor and capabilities.
     * @param accuracy The accuracy as an integer value.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
