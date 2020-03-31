package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Education extends AppCompatActivity {

    SQLiteDatabase mydb;
    int id;
    EditText course,institute,year,cgpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__education);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name1  = globalVariable.getName();
        course=findViewById(R.id.txtcourse);
        institute=findViewById(R.id.txtinstitute);
        year=findViewById(R.id.txtyear);
        cgpa=findViewById(R.id.txtcgpa);

        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS EDUCATION (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,COURSE VARCHAR(255),INSTITUTE VARCHAR(255),CGPA VARCHAR(255),YEAR VARCHAR(255))");
        Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name1+"'",null);
        if (c.moveToFirst())
        {
            id=c.getInt(0);
        }
    }

    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }

    public void onSave(View view) {
        if (id!=0)
        {
            mydb.execSQL("insert into EDUCATION (MAIN_ID,COURSE,INSTITUTE,CGPA,YEAR) values('"+id+"','"+course.getText().toString()+"','"+institute.getText().toString()+"','"+cgpa.getText().toString()+"','"+year.getText().toString()+"')");
            Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
        }

        Intent i1=new Intent(getApplicationContext(),Education_page.class);//i have to chage .class file
        startActivity(i1);
        finish();
    }
}
