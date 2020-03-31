package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Show_project extends AppCompatActivity {

    TextView edt,edt1,edt2;
    EditText name,des,time;
    Button btnupdate,btnsave;
    LinearLayout parentLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_project);

        Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("name");
        String str1=bundle.getString("des");
        String str2=bundle.getString("time");
        edt=findViewById(R.id.txtprojectname1);
        edt1=findViewById(R.id.txtprojectdescription1);
        edt2=findViewById(R.id.txtprojectduration1);
        btnupdate=findViewById(R.id.btnadd);
        btnsave=findViewById(R.id.btnsave);
        parentLinearLayout=findViewById(R.id.parent);

        edt.setText(str);
        edt1.setText(str2);
        edt2.setText(str2);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.update_project, null, false);
                parentLinearLayout.addView(rowView);
                btnupdate.setEnabled(false);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                des=findViewById(R.id.projectdescription);
                time=findViewById(R.id.projectduration);
                name=findViewById(R.id.projectname);
                SQLiteDatabase mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
                mydb.execSQL("CREATE TABLE IF NOT EXISTS PROJECT (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,PROJECT_NAME VARCHAR(255),PROJECT_DESCRIPTION VARCHAR(255),PROJECT_DURATION VARCHAR(255))");
                /*Cursor c1=mydb.rawQuery("select * from PROJECT where PROJECT_NAME='"+edt.getText().toString()+"'",null);
                if (c1.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Database Empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (c1.moveToFirst())
                {*/
                    mydb.execSQL("update PROJECT set PROJECT_NAME='"+name.getText().toString()+"',PROJECT_DESCRIPTION='"+des.getText().toString()+"',PROJECT_DURATION='"+time.getText().toString()+"' where PROJECT_NAME='"+edt.getText().toString()+"'");
                    Toast.makeText(getApplicationContext(),"Data Updated.",Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getApplicationContext(), Project_page.class);
                startActivity(i1);
                finish();

                /*}
                else {
                    Toast.makeText(getApplicationContext(),"Data not Found.",Toast.LENGTH_SHORT).show();
                }*/
            }

        });


    }

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }

    /*public void onupdate(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.update_project, null, false);
        LinearLayout parentLinearLayout = null;
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        name=findViewById(R.id.projectdescription);
        des=findViewById(R.id.projectduration);
        time=findViewById(R.id.projectname);
        SQLiteDatabase mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        Cursor c1=mydb.rawQuery("select * from PROJECT where PROJECT_NAME='"+edt.getText().toString()+"'",null);
        if (c1.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"Database Empty.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (c1.moveToFirst())
        {
            mydb.execSQL("update PROJECT set PROJECT_NAME='"+name.getText().toString()+"',PROJECT_DESCRIPTION='"+des.getText().toString()+"',PROJECT_DURATION='"+time.getText().toString()+"' where PROJECT_NAME='"+edt.getText().toString()+"'");
            Toast.makeText(getApplicationContext(),"Data Updated.",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(),"Data not Found.",Toast.LENGTH_SHORT).show();
        }
    }*/
}
