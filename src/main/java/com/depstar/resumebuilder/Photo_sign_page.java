package com.depstar.resumebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class Photo_sign_page extends AppCompatActivity {

    SQLiteDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_sign_page);

        Button button = findViewById(R.id.pickimg);
        Button button1 = findViewById(R.id.scansign);
        Button removeimg = findViewById(R.id.removeimg);
        final ImageView imageview = findViewById(R.id.imageview);
        mydb = openOrCreateDatabase("resumebuilder", getApplicationContext().MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS PHOTO (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIN_ID INTEGER,PHOTO BLOB,SIGN BLOB)");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");

                startActivityForResult(Intent.createChooser(intent1, "Scan signature"), 2);
            }
        });

        removeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview.setImageResource(0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            ImageView imageView = findViewById(R.id.imageview);

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        if (resultCode == RESULT_OK && requestCode == 2) {
            ImageView imageView1 = findViewById(R.id.imageview1);

            try {
                InputStream inputStream1 = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream1);
                imageView1.setImageBitmap(bitmap1);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void onhome(View view) {
        Intent it=new Intent(this,Create_page.class);
        startActivity(it);
        finish();
    }
}
