package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);
    }

    public void onCreate(View view) {
        Intent intent = new Intent(this,Create_page.class);
        startActivity(intent);
    }

    public void onDownload(View view) {
    }

    public void onRateUs(View view) {
        Intent intent = new Intent(this,Rate_page.class);
        startActivity(intent);
    }

    public void onContactUs(View view) {
        Intent intent = new Intent(this,Contact_page.class);
        startActivity(intent);
    }
}

