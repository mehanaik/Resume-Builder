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

public class Objectives_page extends AppCompatActivity {

    SQLiteDatabase mydb;
    EditText txtobj;
    Button btnsave;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectives_page);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name1  = globalVariable.getName();
        txtobj=findViewById(R.id.txtobjective);

        btnsave=findViewById(R.id.btnsave);

        mydb=openOrCreateDatabase("resumebuilder",getApplicationContext().MODE_PRIVATE,null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS OBJECTIVE (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID VARCHAR(255),OBJECTIVE VARCHAR(255))");

       Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name1+"'",null);
        if (c.moveToFirst())
        {
            id=c.getInt(0);
        }
        //Insert data into database
        /*btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id!=0)
                {
                    mydb.execSQL("INSERT INTO OBJECTIVE (MAIN_ID,OBJECTIVE) values('"+id+"','"+txtobj.getText().toString()+"')");
                    Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
                }


            }
        });*/
    }

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }

    public void onSave(View view) {
        if (id!=0)
        {
            mydb.execSQL("INSERT INTO OBJECTIVE (MAIN_ID,OBJECTIVE) values('"+id+"','"+txtobj.getText().toString()+"')");
            Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
        }
    }
}
