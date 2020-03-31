package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_experience extends AppCompatActivity {

    SQLiteDatabase mydb;
    int id;
    EditText organisation,duration,designation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name1  = globalVariable.getName();
        organisation=findViewById(R.id.txtorganization);
        designation=findViewById(R.id.txtdesignation);
        duration=findViewById(R.id.txtduration);

        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS EXPERIENCE (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,ORGANIZATION VARCHAR(255),DESIGNATION VARCHAR(255),DURATION VARCHAR(255))");
        Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name1+"'",null);
        if (c.moveToFirst())
        {
            id=c.getInt(0);
        }
    }

    public void onSave(View view) {
        if (id!=0)
        {
            mydb.execSQL("INSERT INTO EXPERIENCE(MAIN_ID,ORGANIZATION,DESIGNATION,DURATION) values('"+id+"','"+organisation.getText().toString()+"','"+designation.getText().toString()+"','"+duration.getText().toString()+"')");
            Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
        }

        Intent i1=new Intent(getApplicationContext(),Experience_page.class);//i have to chage .class file
        startActivity(i1);
        finish();
    }
    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }
}
