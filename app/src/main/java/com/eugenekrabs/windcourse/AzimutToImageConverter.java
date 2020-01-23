package com.eugenekrabs.windcourse;

import android.util.Log;

public class AzimutToImageConverter {

    public String format(float azimuth, float diff) {
        Log.d("L", "az: " + azimuth + " | diff: " + diff);

        azimuth = (azimuth - diff);


        int rounded = (int)Math.floor((azimuth + 5) / 10) * 10;
        rounded  = 360 - rounded;

        if (rounded > 360) {
            rounded = rounded - 360;
        }

        Log.d("L", "az-calc: " + rounded);


        if (rounded < 0 ) {
            rounded = Math.abs(rounded);
        }

        Log.d("L", "final: " + rounded);

        if (rounded == 0 || rounded == 360) {
            return "i0";
        } else if (rounded == 180) {
            return "i180";
        } else {
            return "i" + rounded + 's';
        }
    }
}
