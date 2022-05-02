package com.pastillasCreator.pill_box.generic_activityes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.almacenaje.Accumulator;

import java.util.List;

public abstract class AccumulatorActivity extends DefaultActivity{

    protected Accumulator<?> accumulator;
    protected Integer listId;

    protected Class<?> creatorClass;
    protected Class<?> editorClass;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listViewer();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void listViewer() {
        List<String> nameList = accumulator.getNameListOfElements();
        ListView listView = findViewById(listId);

        int layout = android.R.layout.simple_list_item_1;
        ListAdapter arrayAdapter = new ArrayAdapter<>(this,layout,nameList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            Intent i = new Intent(context, editorClass);
            i.putExtra("posicion", Integer.toString(position));
            startActivity(i);
        });
    }

    public void addElement(View view) {
        startActivity(new Intent(getApplicationContext(), creatorClass));
    }

    public Accumulator<?> getAccumulator() {
        return accumulator;
    }

    public Integer getListId() {
        return listId;
    }

    public Class<?> getCreatorClass() {
        return creatorClass;
    }

    public Class<?> getEditorClass() {
        return editorClass;
    }


}
