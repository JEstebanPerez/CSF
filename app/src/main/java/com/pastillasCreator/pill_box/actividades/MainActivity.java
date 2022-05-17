
package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

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
        Switch swi = findViewById(R.id.switch1);
        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    compoundButton.setText("Night");
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    compoundButton.setText("Light");
                }
            }
        });

        boolean isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        swi.setChecked(isNightModeOn);

        if (isNightModeOn){
            swi.setText(("Night Mode"));
        }
        else{
            swi.setText("Light Mode");
        }
    }

    @Override
    public  void recreate(){
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
