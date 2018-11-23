package com.nordwest.university_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Timetable extends AppCompatActivity  /* ListActivity implements AdapterView.OnItemClickListener*/ {

    TimetableAdaptor timetableAdaptor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    RecyclerView rv;
    TextView groupID;
    Button floorPlan, checkMoodle;

    List<TimetableClassHolder> retrivedClasses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        groupID = findViewById(R.id.libraryGroupID);
        checkMoodle = findViewById(R.id.checkMoodle);
        floorPlan = findViewById(R.id.goToFloorplan);
        groupID.setText(Contract.StudentEntry.actualUserGroupName);



        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        rv = findViewById(R.id.rvClasses);
        timetableAdaptor = new TimetableAdaptor(this, retrivedClasses);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //retrivedClasses.clear();
        String choice, time, date, room, subject;
        choice = Contract.StudentEntry.actualUserGroupName.trim();
        try{

        cursor = db.rawQuery("SELECT _classes_._time_ as  'Starting time' , _date_ AS 'Date', _module_._module_name_ as 'Subject' " +
                "from _classes_, _module_ " +
                "where _group_id_ =? and _classes_._module_code_ = _module_._module_code_  ORDER BY _date_ ASC ", new String[]{choice});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                        time = cursor.getString(cursor.getColumnIndex("Starting time")).trim();


                        subject = cursor.getString(cursor.getColumnIndex("Subject")).trim();
                        if ((cursor.getString(cursor.getColumnIndex("Date")).trim()).equals("2018-11-12")){
                            date = "Monday";
                        }else{
                            date = "Tuesday";
                        }
                        if (Contract.StudentEntry.actualUserGroupName.equals("IT01")){
                            room = "RG01";
                        }else {
                            room = "RL01";
                        }
                        retrivedClasses.add(new TimetableClassHolder(time, subject, date, room));

                        rv.setAdapter(timetableAdaptor);


                } while (cursor.moveToNext());
            } else {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



        checkMoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent goToMoodle = new Intent(Timetable.this, WebDashboard.class);
               startActivity(goToMoodle);

            }
        });

        floorPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeFlorPlanSlider = new Intent(Timetable.this, SliderActivity.class);
                startActivity(seeFlorPlanSlider);
            }
        });


    }




}