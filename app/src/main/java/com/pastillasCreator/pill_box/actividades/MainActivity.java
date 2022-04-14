
package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.generic_activityes.DefaultActivity;

public class MainActivity extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.home;
        selectedContentView = R.layout.activity_main;

        context = MainActivity.this;

        super.onCreate(savedInstanceState);
    }
}
