
package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;

public class MainActivity extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.home;
        super.selectedContentView = R.layout.activity_main;
        super.onCreate(savedInstanceState);
    }
}
