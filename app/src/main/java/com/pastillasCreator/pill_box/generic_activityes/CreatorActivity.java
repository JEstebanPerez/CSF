package com.pastillasCreator.pill_box.generic_activityes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.almacenaje.Accumulator;
import com.pastillasCreator.pill_box.creadorElementosCalendario.CalendarElementCreator;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;

public abstract class CreatorActivity<T extends CalendarElement> extends BuilderActivity<T> {

    protected Class<? extends DefaultActivity> menu;
    protected CalendarElementCreator<T> elementCreator;
    protected T elementToUpdate = null;
    protected Accumulator<T> accumulator;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent incomingIntent = getIntent();
        try {
            int position = Integer.parseInt(incomingIntent.getStringExtra("posicion"));
            elementToUpdate = accumulator.getElement(position);
        }catch(NumberFormatException e){
            elementToUpdate = null;
        }


        if(elementToUpdate != null){
            name = elementToUpdate.getName();
            hour = elementToUpdate.getHour();
            description = elementToUpdate.getDescription();
            date = elementToUpdate.getStringShowingDate();

            activityNameEditText.setText(name);
            activityDescriptionEditText.setText(description);
            hourTextView.setText(hour);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNewItem(View view) {
        try{
            if(elementToUpdate != null){
                elementCreator.editElement(elementToUpdate);
            }else{
                elementCreator.saveElement();
            }
            startActivity(new Intent(context, menu));
        } catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
