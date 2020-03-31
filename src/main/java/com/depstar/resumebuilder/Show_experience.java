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

public class Show_experience extends AppCompatActivity {

    TextView organisation,designation,duration;
    EditText edorganisation,eddesignation,edduration;
    Button btnupdate,btnsave;
    LinearLayout parentLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_experience);

        Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("organisation");
        String str1=bundle.getString("designation");
        String str2=bundle.getString("duration");

        organisation=findViewById(R.id.showorganisation);
        designation=findViewById(R.id.showdesignation);
        duration=findViewById(R.id.showduration);

        parentLinearLayout=findViewById(R.id.parent);
        btnupdate=findViewById(R.id.btnupdate);
        organisation.setText(str);
        designation.setText(str1);
        duration.setText(str2);


    }

    public void onSave(View view) {
        edorganisation=findViewById(R.id.updateorganisation);
        eddesignation=findViewById(R.id.updatedesignation);
        edduration=findViewById(R.id.updateduration);

        SQLiteDatabase mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS EXPERIENCE (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,ORGANIZATION VARCHAR(255),DESIGNATION VARCHAR(255),DURATION VARCHAR(255))");

                /*Cursor c1=mydb.rawQuery("select * from PROJECT where PROJECT_NAME='"+edcourse.getText().toString()+"'",null);
                if (c1.getCount()==0)
                {

                }COURSE VARCHAR(255),INSTITUTE VARCHAR(255),CGPA VARCHAR(255),YEAR VARCHAR(255)
                if (c1.moveToFirst())

                {*/
        mydb.execSQL("update EXPERIENCE set ORGANIZATION='"+edorganisation.getText().toString()+"',DESIGNATION='"+eddesignation.getText().toString()+"',DURATION='"+edduration.getText().toString()+"' where ORGANIZATION='"+organisation.getText().toString()+"'");
        Toast.makeText(getApplicationContext(),"Data Updated.",Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(getApplicationContext(), Experience_page.class);
        startActivity(i1);
        finish();
    }


    public void onupdate(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.update_experience, null, false);
        parentLinearLayout.addView(rowView);
        btnupdate.setEnabled(false);

    }

    public void onhome(View view) {
        Intent i1=new Intent(getApplicationContext(),Create_page.class);
        startActivity(i1);
        finish();
    }

}
