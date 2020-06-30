package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.pms.minigames.R;
import de.pms.minigames.main.views.PaintView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;
import java.util.UUID;


/**
 * The PaintActivity manages the activities of the paint screen and initializes a PaintView.
 */
public class PaintActivity extends AppCompatActivity {

    private int countDrawable = 0;
    private PaintView paintView;
    private Button buttonReset, buttonBlack, buttonYellow, buttonRed, buttonGreen, buttonBlue,
            buttonBrown, buttonPurple, buttonErase, buttonStrokeWidth, buttonOutline, buttonSave;

    /**
     * The state of the activity can be saved.
     * Necessary elements like buttons or the PaintView get initialized.
     *
     * @param savedInstanceState Saves the state.
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
        buttonBrown = (Button) findViewById(R.id.brown);
        buttonPurple = (Button) findViewById(R.id.purple);
        buttonErase = (Button) findViewById(R.id.erase);
        buttonStrokeWidth = (Button) findViewById(R.id.strokeWidth);
        buttonOutline = (Button) findViewById(R.id.outline);
        buttonSave = (Button) findViewById(R.id.save_image);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Paint");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        buttonBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(7);
            }
        });
        buttonPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(8);
            }
        });
        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(6);
            }
        });
        buttonStrokeWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDrawable > 2) {
                    countDrawable = 0;
                }
                countDrawable++;
                paintView.setBrushSize(countDrawable);
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });
        buttonOutline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDrawable();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Bitmap cache = paintView.saveImage();

                    MediaStore.Images.Media.insertImage(getContentResolver(), cache,
                            UUID.randomUUID().toString() + ".png", "drawing");
                    Toast.makeText(getApplicationContext(), "Image saved to gallery.",
                            Toast.LENGTH_SHORT).show();

                } else {
                    ActivityCompat.requestPermissions(PaintActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        });
    }

    /**
     * setDrawable can be called by a button click to put outline examples into the background
     * of the paintView.
     */
    private void setDrawable() {
        if (countDrawable > 5) {
            countDrawable = 0;
        }
        countDrawable++;
        switch (countDrawable) {
            case 1:
                paintView.clear(R.drawable.appleoutline);
                break;
            case 2:
                paintView.clear(R.drawable.butterflyoutline);
                break;
            case 3:
                paintView.clear(R.drawable.fishoutline);
                break;
            case 4:
                paintView.clear(R.drawable.housoutline);
                break;
            case 5:
                paintView.clear(R.drawable.staroutline);
                break;
            case 6:
                paintView.clear();
                break;
        }
    }
}
