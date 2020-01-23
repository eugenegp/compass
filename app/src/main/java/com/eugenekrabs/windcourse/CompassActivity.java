package com.eugenekrabs.windcourse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;


public class CompassActivity extends AppCompatActivity {

    private static final String TAG = "CompassActivity";

    private Compass compass;

    private String currentImage;
    private float diff = 0.0f;
    private boolean isDiffDefined;
    private AzimutToImageConverter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        converter = new AzimutToImageConverter();

        setupCompass();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    private void setupCompass() {
        compass = new Compass(this);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float azimuth) {
//        Log.d(TAG, azimuth + "");

        if (diff == 0.0f) {
            diff = azimuth;
        }
        String imageName = converter.format(azimuth, diff);


        if (imageName.equals(currentImage)) {
            return;
        }

        int resID = getResources().getIdentifier(imageName,
                "drawable", getPackageName());

        if (resID == 0) {
            return;
        }

        LinearLayout root=(LinearLayout)findViewById(R.id.root);
        root.setBackgroundResource(resID);
        currentImage = imageName;
    }

    private Compass.CompassListener getCompassListener() {
        return new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(final float azimuth) {
                // UI updates only in UI thread
                // https://stackoverflow.com/q/11140285/444966
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adjustArrow(azimuth);
                    }
                });
            }
        };
    }
}
