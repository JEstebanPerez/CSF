package com.pastillasCreator.pill_box.generic_activityes;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

public abstract class DefaultActivity extends AppCompatActivity {

    protected Integer selectedItem = 0;
    protected Integer selectedContentView = 0;
    protected Context context;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(selectedContentView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(selectedItem);

        bottomNavigationView.setOnItemSelectedListener(x -> FunctionsWhenClick.getApply(x, getApplicationContext(), this));
    }

}
