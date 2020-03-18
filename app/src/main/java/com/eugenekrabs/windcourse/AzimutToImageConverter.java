package com.eugenekrabs.windcourse;

import android.util.Log;

public class AzimutToImageConverter {

    public String format(float azimuth, float diff) {
//        Log.d("L", "az: " + azimuth + " | diff: " + diff);

//        azimuth = (azimuth - diff);
        int diffInt = (int)Math.floor((diff + 5) / 10) * 10;

        int rounded = (int)Math.floor((azimuth + 5) / 10) * 10;

//        if (rounded > 360) {
//            rounded = rounded - 360;
//        }

//        Log.d("L", azimuth + " - " + diffInt + " r: " + rounded + " " + (360 - rounded));

        rounded  = 360 - rounded;


        if (rounded < 0 ) {
            rounded = Math.abs(rounded);
        }

//        Log.d("L", "final: " + rounded);

        if (rounded == 0 || rounded == 360) {
            return "i0";
        } else if (rounded == 180) {
            return "i180";
        } else {
            return "i" + rounded + 's';
        }
    }
}
