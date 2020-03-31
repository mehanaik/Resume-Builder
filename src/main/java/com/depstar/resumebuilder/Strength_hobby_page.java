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

public class Strength_hobby_page extends AppCompatActivity {

    SQLiteDatabase mydb;
    EditText txtstrength,txthobby;
    Button btnsave;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_hobby_page);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name1  = globalVariable.getName();
        txtstrength=findViewById(R.id.txtstrength);
        txthobby=findViewById(R.id.txthobby);

        btnsave=findViewById(R.id.btnsave);

        mydb=openOrCreateDatabase("resumebuilder",getApplicationContext().MODE_PRIVATE,null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS STRENGTH (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,STRENGTH VARCHAR(255),HOBBY VARCHAR(255))");
        Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name1+"'",null);
        if (c.moveToFirst())
        {
            id=c.getInt(0);
        }
    }

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }

    public void onSave(View view) {
        if (id!=0)
        {
            mydb.execSQL("INSERT INTO STRENGTH(MAIN_ID,STRENGTH,HOBBY) values('"+id+"','"+txtstrength.getText().toString()+"','"+txthobby.getText().toString()+"')");
            Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
        }



    }
}