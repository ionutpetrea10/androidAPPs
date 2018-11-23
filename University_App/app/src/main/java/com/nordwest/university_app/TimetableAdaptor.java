package com.nordwest.university_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TimetableAdaptor extends RecyclerView.Adapter<TimetableAdaptor.myViewHolder> {

    Dialog dialog;
    Context context;
    List<TimetableClassHolder> mClasses;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    TextView closePopup;

    public TimetableAdaptor(Context context, List<TimetableClassHolder> mClasses) {
        this.context = context;
        this.mClasses = mClasses;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.timetable_card_model, parent, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        holder.dateClass.setText(mClasses.get(position).getDateClass());
        holder.subjectClass.setText(mClasses.get(position).getSubjectClass());
        holder.timeClass.setText(mClasses.get(position).getTimeClass());
        holder.roomClass.setText(mClasses.get(position).getRoomClass());

    }

    @Override
    public int getItemCount() {
        return mClasses.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timeClass, subjectClass, dateClass, roomClass;


        public myViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            timeClass = itemView.findViewById(R.id.timeClass);
            subjectClass = itemView.findViewById(R.id.subjectClass);
            dateClass = itemView.findViewById(R.id.dateClass);
            roomClass = itemView.findViewById(R.id.roomClass);



        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Intent intent = new Intent(context, Singele_flor_planActivity.class).putExtra(Singele_flor_planActivity.FLOOR_ID, mClasses.get(position).getRoomClass());
            context.startActivity(intent);


            /*dialog = new Dialog(context);
            dialog.setContentView(R.layout.popup_floor_plan);
            dialog.show();
            closePopup = dialog.findViewById(R.id.closePopup);*/


            Toast.makeText(context, mClasses.get(position).roomClass, Toast.LENGTH_SHORT).show();


        }
    }
}
