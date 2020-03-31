package com.depstar.resumebuilder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PracticeDatabase extends AppCompatActivity {

    SQLiteDatabase mydb;

    EditText name,age;
    Button btninsert,btnupdate,btnsearch,btndelete,btnreset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_database);
        mydb=openOrCreateDatabase("test.db", getApplicationContext().MODE_PRIVATE,null);
        mydb.execSQL("create table if not exists basic (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(255),age INTEGER)");

        name=findViewById(R.id.txtmaritalstatus);
        age=findViewById(R.id.txtage);
        btninsert=findViewById(R.id.btninsert);
        btnupdate=findViewById(R.id.btnupdate);
        btnsearch=findViewById(R.id.btnsearch);
        btndelete=findViewById(R.id.btndelete);
        btnreset=findViewById(R.id.btnreset);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.execSQL("insert into basic(name,age) values('"+name.getText().toString()+"','"+age.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=mydb.rawQuery("select * from basic where name='"+name.getText().toString()+"'",null);
                if (c.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Database Empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (c.moveToFirst())
                {
                    age.setText(c.getString(2));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data not Found.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c1=mydb.rawQuery("select * from basic where name='"+name.getText().toString()+"'",null);
                if (c1.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Database Empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (c1.moveToFirst())
                {
                    mydb.execSQL("update basic set age='"+age.getText().toString()+"' where name='"+name.getText().toString()+"'");
                    Toast.makeText(getApplicationContext(),"Data Updated.",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Data not Found.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c2=mydb.rawQuery("select * from basic where name='"+name.getText().toString()+"'",null);
                if (c2.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Database Empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (c2.moveToFirst())
                {
                    mydb.execSQL("delete from basic where age='"+age.getText().toString()+"' and name='"+name.getText().toString()+"'");
                    Toast.makeText(getApplicationContext(),"Data Deleted.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data not Found.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
