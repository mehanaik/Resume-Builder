package com.depstar.resumebuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Education_page extends AppCompatActivity implements EducationAdapter.OnNoteListener {

    RecyclerView recyclerView;
    EducationAdapter educationAdapter;
    List<Education> educationList = new ArrayList<>();
    int id;
    SQLiteDatabase mydb;
    String course,institute,cgpa,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_page);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name = globalVariable.getName();

        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS EDUCATION (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,COURSE VARCHAR(255),INSTITUTE VARCHAR(255),CGPA VARCHAR(255),YEAR VARCHAR(255))");

        recyclerView = findViewById(R.id.recycleeducation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor c1 = mydb.rawQuery("select * from BASIC_DETAILS where NAME='" + name + "'", null);
        if (c1.moveToFirst()) {
            id = c1.getInt(0);
        }
        Cursor c = mydb.rawQuery("select * from EDUCATION where MAIN_ID='"+id+"'", null);

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    id = c.getInt(0);
                    course= c.getString(2);
                    institute = c.getString(3);
                    cgpa = c.getString(4);
                    year=c.getString(5);


                    Education education = new Education(course,institute,cgpa,year);
                    educationList.add(education);
                }
                while (c.moveToNext());
            }
        }

        educationAdapter = new EducationAdapter(this, educationList,  this);
        recyclerView.setAdapter(educationAdapter);
        new ItemTouchHelper(itemcallback).attachToRecyclerView(recyclerView);
        Toast.makeText(getApplicationContext(), course+institute+cgpa+year, Toast.LENGTH_SHORT).show();


    }

    ItemTouchHelper.SimpleCallback itemcallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            educationList.remove(viewHolder.getAdapterPosition());
            educationAdapter.notifyDataSetChanged();

        }
    };

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }

    public void onAdd(View view) {
        Intent i1 = new Intent(getApplicationContext(), Add_Education.class);
        startActivity(i1);
    }
    public void delete(Object id) {

        mydb.execSQL("DELETE FROM EDUCATION WHERE COURSE='"+id+"'");
        Toast.makeText(this, "entery deleted..", Toast.LENGTH_LONG).show();
    }

    public void onNoteClick(int position) {
        educationList.get(position);
        Intent intent=new Intent(this,Show_Education.class);
        intent.putExtra("course",educationList.get(position).getCourse());
        intent.putExtra("institute",educationList.get(position).getInstitute());
        intent.putExtra("cgpa",educationList.get(position).getCgpa());
        intent.putExtra("year",educationList.get(position).getYear());
        startActivity(intent);
    }
}
