package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Show_Education extends AppCompatActivity {

    TextView course,institute,cgpa,year;
    EditText edcourse,edinstitute,edcgpa,edyear;
    Button btnupdate,btnsave;
    LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__education);

        Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("course");
        String str1=bundle.getString("institute");
        String str2=bundle.getString("cgpa");
        String str3=bundle.getString("year");

        course=findViewById(R.id.showcourse);
        institute=findViewById(R.id.showinstitute);
        cgpa=findViewById(R.id.showcgpa);
        year=findViewById(R.id.showyear);
        parentLinearLayout=findViewById(R.id.parent);
        btnupdate=findViewById(R.id.btnupdate);
        course.setText(str);
        institute.setText(str1);
        cgpa.setText(str2);
        year.setText(str3);

    }

    public void onupdate(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.update_education, null, false);
        parentLinearLayout.addView(rowView);
        btnupdate.setEnabled(false);

    }

    public void onSave(View view) {

        edcourse=findViewById(R.id.updatecourse);
        edinstitute=findViewById(R.id.updateinstitute);
        edcgpa=findViewById(R.id.updatecgpa);
        edyear=findViewById(R.id.updateyear);

        SQLiteDatabase mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS EDUCATION (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,COURSE VARCHAR(255),INSTITUTE VARCHAR(255),CGPA VARCHAR(255),YEAR VARCHAR(255))");
                /*Cursor c1=mydb.rawQuery("select * from PROJECT where PROJECT_NAME='"+edcourse.getText().toString()+"'",null);
                if (c1.getCount()==0)
                {

                }COURSE VARCHAR(255),INSTITUTE VARCHAR(255),CGPA VARCHAR(255),YEAR VARCHAR(255)
                if (c1.moveToFirst())
                {*/
        mydb.execSQL("update EDUCATION set COURSE='"+edcourse.getText().toString()+"',INSTITUTE='"+edinstitute.getText().toString()+"',CGPA='"+edcgpa.getText().toString()+"',YEAR='"+edyear.getText().toString()+"' where COURSE='"+course.getText().toString()+"'");
        Toast.makeText(getApplicationContext(),"Data Updated.",Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(getApplicationContext(), Education_page.class);
        startActivity(i1);
        finish();
    }

    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }
}
