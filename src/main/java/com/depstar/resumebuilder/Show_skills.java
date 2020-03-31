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

public class Show_skills extends AppCompatActivity {

    TextView skill;
    EditText edskill;
    Button btnupdate,btnsave;
    LinearLayout parentLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_skills);

        Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("skill");

        skill=findViewById(R.id.showskill);
        parentLinearLayout=findViewById(R.id.parent);
        btnupdate=findViewById(R.id.btnupdate);
        skill.setText(str);
    }

    public void onupdate(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.update_technicalskills, null, false);
        parentLinearLayout.addView(rowView);
        btnupdate.setEnabled(false);
    }

    public void onSave(View view) {
        edskill=findViewById(R.id.updateach);


        SQLiteDatabase mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS SKILL (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,SKILL VARCHAR(255))");
                /*Cursor c1=mydb.rawQuery("select * from PROJECT where PROJECT_NAME='"+edcourse.getText().toString()+"'",null);
                if (c1.getCount()==0)
                {

                }COURSE VARCHAR(255),INSTITUTE VARCHAR(255),CGPA VARCHAR(255),YEAR VARCHAR(255)
                if (c1.moveToFirst())
                {*/
        mydb.execSQL("update SKILL set SKILL='"+edskill.getText().toString()+"' where SKILL='"+skill.getText().toString()+"'");
        Toast.makeText(getApplicationContext(),"Data Updated.",Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(getApplicationContext(), Skills_page.class);
        startActivity(i1);
        finish();
    }

    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }
}
