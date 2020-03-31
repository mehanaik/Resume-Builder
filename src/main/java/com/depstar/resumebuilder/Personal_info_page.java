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

public class Personal_info_page extends AppCompatActivity {

    SQLiteDatabase mydb;
    EditText txtmaritalstatus,txtlanguage,txtlinkedin,txtgithub,txtwebsite,txtcustom;
    Button btnsave;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_page);

        txtmaritalstatus=findViewById(R.id.txtmaritalstatus);
        txtlanguage=findViewById(R.id.txtlanguage);
        txtlinkedin=findViewById(R.id.txtlinkedin);
        txtgithub=findViewById(R.id.txtgithub);
        txtwebsite=findViewById(R.id.txtwebsite);
        txtcustom=findViewById(R.id.txtcustom);

        btnsave=findViewById(R.id.btnsave);

        mydb=openOrCreateDatabase("resumebuilder",getApplicationContext().MODE_PRIVATE,null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS PERSONAL_INFO (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,MARITALSTATUS VARCHAR(255),LANGUAGE VARCHAR(255),LINKEDIN VARCHAR(255),GITHUB VARCHAR(255),WEBSITE VARCHAR(255),CUSTOM VARCHAR(255))");



        //Insert data into database
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                final String name  = globalVariable.getName();
                //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                Cursor c=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name+"'",null);
                if (c.moveToFirst())
                {
                    id=c.getInt(0);
                }
                if (id!=0)
                {
                    mydb.execSQL("insert into PERSONAL_INFO (MAIN_ID,MARITALSTATUS,LANGUAGE,LINKEDIN,GITHUB,WEBSITE,CUSTOM) values('"+id+"','"+txtmaritalstatus.getText().toString()+"','"+txtlanguage.getText().toString()+"','"+txtlinkedin.getText().toString()+"','"+txtgithub.getText().toString()+"','"+txtwebsite.getText().toString()+"','"+txtcustom.getText().toString()+"')");
                    Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }
}
