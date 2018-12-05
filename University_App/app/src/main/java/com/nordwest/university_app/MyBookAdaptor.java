package com.nordwest.university_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyBookAdaptor extends RecyclerView.Adapter<MyBookAdaptor.myViewHolder>  {

    Context context;
    List<MyViewBookHolder> mBooks;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

    public MyBookAdaptor(Context context, List<MyViewBookHolder> mBooks) {
        this.context = context;
        this.mBooks = mBooks;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.model_book, parent, false);

        return new myViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.book_ISBN.setText(mBooks.get(position).getBookISBN());
        holder.book_title.setText(mBooks.get(position).getBookTitle());
        holder.book_author.setText(mBooks.get(position).getBookAuthor());
        holder.book_edition.setText(mBooks.get(position).getBookEdition());
        holder.btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelper = new DatabaseHelper(context);
                ContentValues contentValues = new ContentValues();

                if (Contract.StudentEntry.student_has_reservation){

                    Toast.makeText(context, "You are not allowed to reserve this book", Toast.LENGTH_SHORT).show();

                }else {

                    try {
                        db = openHelper.getWritableDatabase();
                        contentValues.put(Contract.ReservationEntry.BOOK_ID, mBooks.get(position).getBookISBN());
                        contentValues.put(Contract.ReservationEntry.USER_ID, Contract.StudentEntry.actualUserStudentID);
                        db.insert(Contract.ReservationEntry.TABLE_RESERVATION_NAME, null, contentValues);
                        Toast.makeText(context, "Reserved successfully", Toast.LENGTH_LONG).show();
                        Contract.StudentEntry.student_has_reservation = true;
                        holder.btnReserve.setVisibility(View.INVISIBLE);
                        holder.btnCancelReserv.setVisibility(View.VISIBLE);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            }
        });

        holder.btnCancelReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnReserve.setVisibility(View.VISIBLE);
                holder.btnCancelReserv.setVisibility(View.INVISIBLE);
                openHelper = new DatabaseHelper(context);
                db = openHelper.getWritableDatabase();
                db.delete(Contract.ReservationEntry.TABLE_RESERVATION_NAME, Contract.ReservationEntry.USER_ID +" =? ", new String[]{Contract.StudentEntry.actualUserStudentID});
               Contract.StudentEntry.student_has_reservation = false;
                //Toast a message to inform the user about the record being successfully inserted
                Toast.makeText(context, "Reservation canceled", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookISBN = mBooks.get(position).getBookISBN();
                context.startActivity(new Intent(context, ReviewBookActivity.class).putExtra(ReviewBookActivity.BOOK_ID_KEY, bookISBN));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView book_ISBN, book_title,book_author, book_edition, searchedBook;
        Button btnReview, btnReserve, btnCancelReservation, btnCancelReserv;
    public myViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        book_ISBN = itemView.findViewById(R.id.book_ISBN);
        book_title = itemView.findViewById(R.id.roomClass);
        book_author = itemView.findViewById(R.id.book_author);
        book_edition = itemView.findViewById(R.id.subjectClass);
        btnReview = itemView.findViewById(R.id.btnReview);
        searchedBook = itemView.findViewById(R.id.searchedBook);
        btnReserve = itemView.findViewById(R.id.btnReserve);
        btnCancelReserv = itemView.findViewById(R.id.btnCancel);

        if (Contract.StudentEntry.student_has_reservation){
            btnReserve.setVisibility(View.INVISIBLE);
            btnCancelReserv.setVisibility(View.VISIBLE);

        }else {
            btnReserve.setVisibility(View.VISIBLE);
            btnCancelReserv.setVisibility(View.INVISIBLE);

        }

    }

        @Override
        public void onClick(View view) {

        }
    }
}

