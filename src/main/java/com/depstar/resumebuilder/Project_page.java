package com.depstar.resumebuilder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Project_page extends AppCompatActivity implements ProductAdapter.OnNoteListener {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<product> projectList = new ArrayList<>();

    private LinearLayout parentLinearLayout;
    TextView projects, inc, inc1;
    Button add_field_button, save_field_button;
    int i = 0, i1 = 1, id;
    SQLiteDatabase mydb;
    String txtprojectname;
    String txtprojectdescription;
    String txtprojectduration;
    EditText projectname;
    EditText projectdescription;
    EditText projectduration;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_page);
        //parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        //projects = (TextView) findViewById(R.id.projects);
        //projects.setBackgroundResource(R.mipmap.title_bar1);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name = globalVariable.getName();


        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS PROJECT (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,PROJECT_NAME VARCHAR(255),PROJECT_DESCRIPTION VARCHAR(255),PROJECT_DURATION VARCHAR(255))");
        recyclerView = findViewById(R.id.recycleproject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor c1 = mydb.rawQuery("select * from BASIC_DETAILS where NAME='" + name + "'", null);
        if (c1.moveToFirst()) {
            id = c1.getInt(0);
        }
        Cursor c = mydb.rawQuery("select * from PROJECT where MAIN_ID='"+id+"'", null);

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    id = c.getInt(0);
                    txtprojectname = c.getString(2);
                    txtprojectdescription = c.getString(3);
                    txtprojectduration = c.getString(4);


                    product project = new product(txtprojectname, txtprojectdescription, txtprojectduration);
                    projectList.add(project);
                }
                while (c.moveToNext());
            }
        }

        productAdapter = new ProductAdapter(this, projectList,this);
        recyclerView.setAdapter(productAdapter);
        new ItemTouchHelper(itemcallback).attachToRecyclerView(recyclerView);
        Toast.makeText(getApplicationContext(), txtprojectname + txtprojectduration + txtprojectdescription, Toast.LENGTH_SHORT).show();

    }
    ItemTouchHelper.SimpleCallback itemcallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            projectList.remove(viewHolder.getAdapterPosition());
                productAdapter.notifyDataSetChanged();

        }
    };
    public void onAdd(View view) {

        Intent i1 = new Intent(getApplicationContext(), Add_project.class);
        startActivity(i1);
        //Project_getter_setter project=new Project_getter_setter();
        //projectList.add(project);
        //projectAdapter=new ProjectAdapter(this,projectList);
        //recyclerView.setAdapter(projectAdapter);
        /*if (i1 <= 4) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.update_project, null, false);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);


            projectdescription=findViewById(R.id.projectdescription);
            projectduration=findViewById(R.id.projectduration);
            projectname=findViewById(R.id.projectname);

            projectList=new ArrayList<>();
            projectList.add(new Project_getter_setter(
                    projectname.getText().toString(),
                    projectdescription.getText().toString(),
                    projectduration.getText().toString()
            ));
            projectAdapter=new ProjectAdapter(this,projectList);
            recyclerView.setAdapter(projectAdapter);
            Toast.makeText(getApplicationContext(),"projectname: "+projectname.getText().toString()+"Projectdescripation"+projectdescription.getText().toString()+"projectduration"+projectduration.getText().toString(),Toast.LENGTH_SHORT).show();
               /* if (id!=0)
                {
                    mydb.execSQL("insert into PROJECT (MAIN_ID,PROJECT_NAME,PROJECT_DESCRIPTION,PROJECT_DURATION) values('"+id+"','"+projectname.getText().toString()+"','"+projectdescription.getText().toString()+"','"+projectduration.getText().toString()+"')");
                    Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
                }*/


        //i1++;

                /*if (i==0)
            {
                mydb.execSQL("insert into PROJECT (MAIN_ID,PROJECT_NAME,PROJECT_DESCRIPTION,PROJECT_DURATION) values('"+id+"','"+txtprojectname.getText().toString()+"','"+txtprojectdescription.getText().toString()+"','"+txtprojectduration.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"Data Inserted.",Toast.LENGTH_SHORT).show();
                i++;
            }

        } else {

            Toast.makeText(this, "Cannot have more projects!", Toast.LENGTH_LONG).show();
        }*/
        //i1=parentLinearLayout.getChildCount()-2;
        //inc1.setText("Project "+(parentLinearLayout.getChildCount()-2)+":");
    }

    public void delete(Object id) {

        mydb.execSQL("DELETE FROM PROJECT WHERE PROJECT_NAME='"+id+"'");
        Toast.makeText(this, "entery deleted..", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNoteClick(int position) {
        projectList.get(position);
        Intent intent=new Intent(this,Show_project.class);
        intent.putExtra("name",projectList.get(position).getProductname());
        intent.putExtra("des",projectList.get(position).getDesc());
        intent.putExtra("time",projectList.get(position).getTime());
        startActivity(intent);
    }

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }
}



