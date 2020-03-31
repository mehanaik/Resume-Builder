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

public class Experience_page extends AppCompatActivity implements ExperienceAdapter.OnNoteListener {

    RecyclerView recyclerView;
    ExperienceAdapter experienceAdapter;
    List<Experience> experienceList = new ArrayList<>();
    int id;
    SQLiteDatabase mydb;
    String organization,duration,designation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_page);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name = globalVariable.getName();
        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS EXPERIENCE (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,ORGANIZATION VARCHAR(255),DESIGNATION VARCHAR(255),DURATION VARCHAR(255))");
        recyclerView = findViewById(R.id.recycleexperience);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor c1 = mydb.rawQuery("select * from BASIC_DETAILS where NAME='" + name + "'", null);
        if (c1.moveToFirst()) {
            id = c1.getInt(0);
        }
        Cursor c = mydb.rawQuery("select * from EXPERIENCE where MAIN_ID='"+id+"'", null);

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    id = c.getInt(0);
                    organization= c.getString(2);
                    designation= c.getString(3);
                    duration= c.getString(4);

                    Experience experience = new Experience(organization,designation,duration);
                    experienceList.add(experience);
                }
                while (c.moveToNext());
            }
        }

        experienceAdapter = new ExperienceAdapter(this, experienceList,  this);
        recyclerView.setAdapter(experienceAdapter);
        new ItemTouchHelper(itemcallback).attachToRecyclerView(recyclerView);
        Toast.makeText(getApplicationContext(), organization, Toast.LENGTH_SHORT).show();

    }
    ItemTouchHelper.SimpleCallback itemcallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            experienceList.remove(viewHolder.getAdapterPosition());
            experienceAdapter.notifyDataSetChanged();

        }
    };
    public void onAdd(View view) {
        Intent i1 = new Intent(getApplicationContext(), Add_experience.class);
        startActivity(i1);
    }

    public void delete(Object id) {

        mydb.execSQL("DELETE FROM EXPERIENCE WHERE ORGANIZATION='"+id+"'");
        Toast.makeText(this, "entery deleted..", Toast.LENGTH_LONG).show();
    }
    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }
    public void onNoteClick(int position) {
        experienceList.get(position);
        Intent intent=new Intent(this,Show_experience.class);
        intent.putExtra("organisation",experienceList.get(position).getOrganisation());
        intent.putExtra("designation",experienceList.get(position).getDesignation());
        intent.putExtra("duration",experienceList.get(position).getDuration());

        startActivity(intent);
    }
}
