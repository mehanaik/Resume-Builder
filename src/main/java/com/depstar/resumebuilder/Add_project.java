package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_project extends AppCompatActivity {


    SQLiteDatabase mydb;
    int id;
    EditText name,description,duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name1  = globalVariable.getName();
        name=findViewById(R.id.projectname);
        description=findViewById(R.id.projectdescription);
        duration=findViewById(R.id.projectduration);


        /*Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("name");
        if (str!=null)
        {
            name.setText(str);
        }*/

        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS PROJECT (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,PROJECT_NAME VARCHAR(255),PROJECT_DESCRIPTION VARCHAR(255),PROJECT_DURATION VARCHAR(255))");
        Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name1+"'",null);
        if (c.moveToFirst())
        {
            id=c.getInt(0);
        }
        /*Cursor c1=mydb.rawQuery("select * from PROJECT where PROJECT_NAME='"+str+"'",null);
        if (c1.getCount()>0)
        {
            if (c1.moveToFirst())
            {
                id=c1.getInt(0);
                name.setText(c1.getString(2));
                description.setText(c1.getString(3));
                duration.setText(c1.getString(4));
            }
        }*/
    }
    public void onSave(View view) {
        if (id!=0)
        {
            mydb.execSQL("insert into PROJECT (MAIN_ID,PROJECT_NAME,PROJECT_DESCRIPTION,PROJECT_DURATION) values('"+id+"','"+name.getText().toString()+"','"+description.getText().toString()+"','"+duration.getText().toString()+"')");
            Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
        }

        Intent i1=new Intent(getApplicationContext(),Project_page.class);
        startActivity(i1);
        finish();
    }

    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }

}
