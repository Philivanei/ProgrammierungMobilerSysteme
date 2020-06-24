package de.pms.minigames.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.pms.minigames.R;
import de.pms.minigames.main.views.PaintView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * The PaintActivity manages the Activities of the paint screen and initializes a PaintView
 */
public class PaintActivity extends AppCompatActivity {

    private int count = 0;
    private PaintView paintView;
    private Button buttonReset, buttonBlack, buttonYellow, buttonRed, buttonGreen, buttonBlue,
            buttonErase, buttonOutline, buttonSave;

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

        //paintView.registerActivity(this);

        buttonReset = (Button) findViewById(R.id.button_reset);
        buttonBlack = (Button) findViewById(R.id.black);
        buttonRed = (Button) findViewById(R.id.red);
        buttonYellow = (Button) findViewById(R.id.yellow);
        buttonBlue = (Button) findViewById(R.id.blue);
        buttonGreen = (Button) findViewById(R.id.green);
        buttonErase = (Button) findViewById(R.id.erase);
        buttonOutline = (Button) findViewById(R.id.outline);
        buttonSave = (Button) findViewById(R.id.save_image);

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

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.


                    Bitmap cache = paintView.saveImage();
                    Toast saveToast = Toast.makeText(getApplicationContext(),
                            "Image saved to gallery.", Toast.LENGTH_SHORT);

                    String savedImageURL = MediaStore.Images.Media.insertImage(getContentResolver(),cache,UUID.randomUUID().toString() + ".png", "drawing");
                    System.out.println(savedImageURL);

                    //MediaStore.Images.Media.insertImage(getContentResolver(), cache, "Picture",
                    //        "Picture");
                    //MediaStore.Images.Media.insertImage(getContentResolver(),
                    //        paintView.getDrawingCache(), UUID.randomUUID().toString() + ".png",
                    //        "drawing");
                    saveToast.show();

                } else {
                    ActivityCompat.requestPermissions(PaintActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }


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
