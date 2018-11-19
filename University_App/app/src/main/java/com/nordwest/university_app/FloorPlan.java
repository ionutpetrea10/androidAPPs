package com.nordwest.university_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FloorPlan extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);
        Toast.makeText(getBaseContext(),"Floor plan",Toast.LENGTH_LONG).show();

        //makes the statue bar background transparent
       /* Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);*/

        //connecting the RecycleView with the adapter

        RecyclerView recyclerView =  findViewById(R.id.rv_list);
        List<ItemViewConstructor> mlist = new ArrayList<>();

        mlist.add(new ItemViewConstructor(R.drawable.levelone,"Level First", R.drawable.bookmark, "G01, G02 and the Library"));
        mlist.add(new ItemViewConstructor(R.drawable.secondlevel,"Level Second", R.drawable.date, "RG1, RG2, RG3 and RG4"));
        mlist.add(new ItemViewConstructor(R.drawable.thirdlevel,"Level Three", R.drawable.contact, "RL1, RL2, RL3 and Tutor Area"));
        mlist.add(new ItemViewConstructor(R.drawable.ground,"Ground Level", R.drawable.chat, "Student Service, Reception and others"));
        Adaptor adaptor = new Adaptor(this, mlist);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }


}
