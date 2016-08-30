package com.elyeproj.deviceinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private float deviceDensity = 0;
    private ViewGroup containerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerView = (ViewGroup)findViewById(R.id.view_container);

        calculateDensity();
        calculateDeviceInfo();
        calculateStatusBar();
    }

    private void calculateDensity() {
        deviceDensity  = getResources().getDisplayMetrics().density;

        String densityStr = "Undefined";

        if (deviceDensity == 0.75) densityStr = "LDPI";
        else if (deviceDensity == 1.0) densityStr = "MDPI";
        else if (deviceDensity == 1.5) densityStr = "HDPI";
        else if (deviceDensity == 2.0) densityStr = "XHDPI";
        else if (deviceDensity == 3.0) densityStr = "XXHDPI";
        else if (deviceDensity == 4.0) densityStr = "XXXHDPI";

        addTextView("Density Value: " + deviceDensity + "(" + densityStr + ")");
    }

    private void calculateDeviceInfo() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float dpHeight = outMetrics.heightPixels / deviceDensity;
        float dpWidth  = outMetrics.widthPixels / deviceDensity;

        addTextView("Height Resolution(dp): " + dpHeight);
        addTextView("Width Resolution(dp): " + dpWidth);
    }

    private void calculateStatusBar() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            float dpStatusBar = getResources().getDimensionPixelSize(resourceId)/deviceDensity;
            addTextView("Status Bar Resolution(dp)\t: " + dpStatusBar);
        }
    }

    private void addTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        containerView.addView(textView);
    }
}