package com.depstar.resumebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Contact_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
    }

    public void onhome(View view) {
        Intent it=new Intent(this,Dashboard_page.class);
        startActivity(it);
        finish();
    }
}
