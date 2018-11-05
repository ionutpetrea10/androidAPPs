package com.nordwest.university_app;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Timetable extends ListActivity implements AdapterView.OnItemClickListener {

    String [] week_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        week_day = getResources().getStringArray(R.array.week_days);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.week_day_list, week_day);
        getListView().setAdapter(arrayAdapter);

        getListView().setOnItemClickListener(this);
        Toast.makeText(getBaseContext(),"Timetable",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, ((TextView) view).getText(), Toast.LENGTH_LONG).show();
    }
}