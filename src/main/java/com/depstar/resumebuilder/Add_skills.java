package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_skills extends AppCompatActivity {

    SQLiteDatabase mydb;
    int id;
    EditText skill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skills);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name1  = globalVariable.getName();
        skill=findViewById(R.id.txtskill);


        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS SKILL (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,SKILL VARCHAR(255))");
        Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name1+"'",null);
        if (c.moveToFirst())
        {
            id=c.getInt(0);
        }
    }

    public void onSave(View view) {
        if (id!=0)
        {
            mydb.execSQL("INSERT INTO SKILL (MAIN_ID,SKILL) values('"+id+"','"+skill.getText().toString()+"')");
            Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
        }

        Intent i1=new Intent(getApplicationContext(),Skills_page.class);//i have to chage .class file
        startActivity(i1);
        finish();
    }
    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }
}
