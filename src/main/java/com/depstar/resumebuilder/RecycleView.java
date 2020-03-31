package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity implements ProductAdapter.OnNoteListener  {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<product> productList;
    SQLiteDatabase mydb;
    String txtprojectname;
    String txtprojectdescription;
    String txtprojectduration;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS PROJECT (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,PROJECT_NAME VARCHAR(255),PROJECT_DESCRIPTION VARCHAR(255),PROJECT_DURATION VARCHAR(255))");

        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList=new ArrayList<>();
        productList.add(new product("kill"));
        productList.add(new product("necklace"));
        productList.add(new product("mugs"));

        Cursor c=mydb.rawQuery("select * from PROJECT",null);

        if (c.getCount()>0)
        {
            if (c.moveToFirst())
            {
                do {
                id=c.getInt(0);
                txtprojectname=c.getString(1);
                txtprojectdescription=c.getString(2);
                txtprojectduration=c.getString(3);
                Toast.makeText(getApplicationContext(),txtprojectname+txtprojectduration+txtprojectdescription,Toast.LENGTH_SHORT).show();


                    product project=new product(txtprojectname,txtprojectdescription,txtprojectduration);
                    productList.add(project);
                }
                while(c.moveToNext());
            }
        }

        productAdapter=new ProductAdapter(this,productList,this);
        recyclerView.setAdapter(productAdapter);

    }

    @Override
    public void onNoteClick(int position) {

    }
}
