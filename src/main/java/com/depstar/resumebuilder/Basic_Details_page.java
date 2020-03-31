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

public class Basic_Details_page extends AppCompatActivity {

    SQLiteDatabase mydb;
    EditText txtname,txtaddress1,txtaddress2,txtaddress3,txtbirthdate,txtnationality,txtphoneno,txtemail;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details_page);

        txtname=findViewById(R.id.txtname);
        txtaddress1=findViewById(R.id.txtaddress1);
        txtaddress2=findViewById(R.id.txtaddress2);
        txtaddress3=findViewById(R.id.txtaddress3);
        txtemail=findViewById(R.id.txtemail);
        txtphoneno=findViewById(R.id.txtphoneno);
        txtbirthdate=findViewById(R.id.txtbirthdate);
        txtnationality=findViewById(R.id.txtnationality);
        btnsave=findViewById(R.id.btnsave);

        mydb=openOrCreateDatabase("resumebuilder",getApplicationContext().MODE_PRIVATE,null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS BASIC_DETAILS (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(255),ADDRESS1 VARCHAR(255),ADDRESS2 VARCHAR(255),ADDRESS3 VARCHAR(255),EMAIL VARCHAR(255),PHONENO INTERGER(255),BIRTHDATE DATE,NATIONALITY VARCHAR(255))");

        //Insert data into database
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=mydb.rawQuery("select NAME from BASIC_DETAILS where NAME='"+txtname.getText().toString()+"'",null);
                if (c.moveToFirst())
                {
                    Toast.makeText(getApplicationContext(),"Data already exists.",Toast.LENGTH_SHORT).show();
                }
                else {
                    final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                    globalVariable.setName(txtname.getText().toString());
                    Toast.makeText(getApplicationContext(),globalVariable.getName(),Toast.LENGTH_SHORT).show();
                    mydb.execSQL("insert into BASIC_DETAILS (NAME,ADDRESS1,ADDRESS2,ADDRESS3,EMAIL,PHONENO,BIRTHDATE,NATIONALITY) values('"+txtname.getText().toString()+"','"+txtaddress1.getText().toString()+"','"+txtaddress2.getText().toString()+"','"+txtaddress3.getText().toString()+"','"+txtemail.getText().toString()+"','"+txtphoneno.getText().toString()+"','"+txtbirthdate.getText().toString()+"','"+txtnationality.getText().toString()+"')");
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
