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

public class Achievement_page extends AppCompatActivity implements AchievementAdapter.OnNoteListener {

    RecyclerView recyclerView;
    AchievementAdapter achievementAdapter;
    List<Achievement> achievementList = new ArrayList<>();
    int id;
    SQLiteDatabase mydb;
    String achive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_page);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name = globalVariable.getName();

        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS ACHIVEMENT (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,ACHIVEMENT VARCHAR(255))");
        recyclerView = findViewById(R.id.recycleachivement);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor c1 = mydb.rawQuery("select * from BASIC_DETAILS where NAME='" + name + "'", null);
        if (c1.moveToFirst()) {
            id = c1.getInt(0);
        }
        Cursor c = mydb.rawQuery("select * from ACHIVEMENT where MAIN_ID='"+id+"'", null);

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    id = c.getInt(0);
                    achive= c.getString(2);

                    Achievement achievement = new Achievement(achive);
                    achievementList.add(achievement);
                }
                while (c.moveToNext());
            }
        }

        achievementAdapter = new AchievementAdapter(this, achievementList,  this);
        recyclerView.setAdapter(achievementAdapter);
        new ItemTouchHelper(itemcallback).attachToRecyclerView(recyclerView);
        Toast.makeText(getApplicationContext(), achive, Toast.LENGTH_SHORT).show();

    }

    ItemTouchHelper.SimpleCallback itemcallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            achievementList.remove(viewHolder.getAdapterPosition());
            achievementAdapter.notifyDataSetChanged();

        }
    };
    public void onAdd(View view) {
        Intent i1 = new Intent(getApplicationContext(), Add_achievement.class);
        startActivity(i1);
    }

    public void delete(Object id) {

        mydb.execSQL("DELETE FROM ACHIVEMENT WHERE ACHIVEMENT='"+id+"'");
        Toast.makeText(this, "entery deleted..", Toast.LENGTH_LONG).show();
    }
    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }
    public void onNoteClick(int position) {
        achievementList.get(position);
        Intent intent=new Intent(this,Show_achievement.class);
        intent.putExtra("achive",achievementList.get(position).getAchive());
        startActivity(intent);
    }
}
