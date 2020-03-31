package com.depstar.resumebuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static com.itextpdf.text.BaseColor.RED;

public class Create_page extends AppCompatActivity {

    Button basicdetails,personalinfo,education,projects,skills,objectives,interest,experience,achievement,strength,sign,generate;
    SQLiteDatabase mydb;
    private static final int STORAGE_WRITE_CODE = 2000;
    private static final int STORAGE_READ_CODE = 3000;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);


        mydb=openOrCreateDatabase("resumebuilder",getApplicationContext().MODE_PRIVATE,null);
        //mydb.execSQL("CREATE TABLE IF NOT EXISTS BASIC_DETAILS (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(255),ADDRESS1 VARCHAR(255),ADDRESS2 VARCHAR(255),ADDRESS3 VARCHAR(255),EMAIL VARCHAR(255),PHONENO INTERGER(255),BIRTHDATE DATE,NATIONALITY VARCHAR(255))");

        basicdetails=findViewById(R.id.btndetails);
        personalinfo=findViewById(R.id.btnpersonalinfo);
        education=findViewById(R.id.btneducation);
        projects=findViewById(R.id.btnprojects);
        skills=findViewById(R.id.btnskills);
        objectives=findViewById(R.id.btnobjectives);
        interest=findViewById(R.id.btninterest);
        experience=findViewById(R.id.btnexperience);
        achievement=findViewById(R.id.btnachievement);
        strength=findViewById(R.id.btnstrength);
        sign=findViewById(R.id.btnsignature);
        generate=findViewById(R.id.btngenerate);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        basicdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Basic_Details_page.class);
                startActivity(i1);
            }
        });

        personalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Personal_info_page.class);
                startActivity(i1);
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Education_page.class);
                startActivity(i1);

            }
        });

        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Project_page.class);
                startActivity(i1);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Photo_sign_page.class);
                startActivity(i1);
            }
        });

        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Achievement_page.class);
                startActivity(i1);
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Skills_page.class);
                startActivity(i1);
            }
        });

        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Interest_page.class);
                startActivity(i1);
            }
        });
        objectives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Objectives_page.class);
                startActivity(i1);
            }
        });
        strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Strength_hobby_page.class);
                startActivity(i1);
            }
        });
        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),Experience_page.class);
                startActivity(i1);
            }
        });
    }

    public void onhome(View view) {
        Intent it=new Intent(this,Dashboard_page.class);
        startActivity(it);
        finish();
    }

    public void onGenerate(View view) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, STORAGE_READ_CODE);

            }
            else{
                // permission already granted
                createPDFMethod();
            }
        }
        else{
            // system OS < Oreao, call generate pdf method
            createPDFMethod();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permission, STORAGE_READ_CODE);

            }
            else{
                // permission already granted
                viewPdfMethod();
            }
        }
        else{
            // system OS < Oreao, call generate pdf method
            viewPdfMethod();
        }

    }

    private void createPDFMethod() {
        Document doc=new Document();
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        final String name  = globalVariable.getName();
        Cursor forid=mydb.rawQuery("select id from BASIC_DETAILS where NAME='"+name+"'",null);
        if (forid.moveToFirst())
        {
            id=forid.getInt(0);
        }
        Cursor c=mydb.rawQuery("select * from BASIC_DETAILS where NAME='"+name+"'",null);
        Cursor c8=mydb.rawQuery("select * from PERSONAL_INFO where MAIN_ID='"+id+"'",null);
        Cursor c1=mydb.rawQuery("select * from OBJECTIVE where MAIN_ID='"+id+"'",null);
        Cursor c7=mydb.rawQuery("select * from STRENGTH where MAIN_ID='"+id+"'",null);
        Cursor c4= mydb.rawQuery("select * from SKILL where MAIN_ID='"+id+"'", null);
        Cursor c5= mydb.rawQuery("select * from PROJECT where MAIN_ID='"+id+"'", null);
        Cursor c6= mydb.rawQuery("select * from INTERESTS where MAIN_ID='"+id+"'", null);
        Cursor c2= mydb.rawQuery("select * from EXPERIENCE where MAIN_ID='"+id+"'", null);
        Cursor c3= mydb.rawQuery("select * from EDUCATION where MAIN_ID='"+id+"'", null);
        Cursor c9= mydb.rawQuery("select * from ACHIVEMENT where MAIN_ID='"+id+"'", null);

//        BaseColor myColor = WebColors.GetRGBColor("#A00000");

        String outpath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Resumes";
        try {

            File dir = new File(outpath);
            if (!dir.exists()){
                dir.mkdirs();
            }

            File nFilePath = new File(dir,"Resume.pdf");
            PdfWriter writer=PdfWriter.getInstance(doc,new FileOutputStream(nFilePath));
            Font fontSize_30 =  FontFactory.getFont(FontFactory.TIMES, 30f);
            Font fontSize_10=FontFactory.getFont(FontFactory.TIMES, 15f);
            Font fontSize_20=FontFactory.getFont(FontFactory.TIMES, 20f);

//            String str=new String("Resume");
//            Font font=new Font(str);
//            font.setColor(str.getC);
            doc.open();

            PdfPTable table1=new PdfPTable(1);
//            table1.getDefaultCell().setBorder(Rectangle.BOTTOM);
//            table1.getDefaultCell().setBorderColorBottom(RED);
//            table1.getDefaultCell().setBorderWidth(5);
            table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table1.getDefaultCell().setPadding(10f);
            table1.setWidthPercentage(100);

            //doc.add();

            while (c.moveToNext())
            {
                String name1,add1,add2,add3,phonenno,email;
                name1=c.getString(1);
                add1=c.getString(2);
                add2=c.getString(3);
                add3=c.getString(4);
                phonenno=c.getString(6);
                email=c.getString(5);


                table1.addCell(new Paragraph(name1,fontSize_30));
                table1.addCell(new Paragraph(add1+"\n"+add2+"\n"+add3+"\n"+phonenno+"\n"+email,fontSize_10));
//                table1.addCell(add2);
//                table1.addCell(add3);
//                table1.addCell(phonenno);
//                table1.addCell(email);
            }
            doc.add(table1);

            //set the horizontal line
//            Paragraph p1=new Paragraph("CAREER OBJECTIVE",fontSize_10);
////            p1.setSpacingAfter(20);
//            PdfContentByte canvas = writer.getDirectContent();
////            Paint paint = new Paint();
////            paint.setColor(Color.YELLOW);
//            canvas.saveState();
//            canvas.setLineWidth((float) 10 / 10);
//            canvas.moveTo(40, 1000);
//            canvas.lineTo(555, 1000);
//            canvas.stroke();
//            doc.add(p1);
//            canvas.restoreState();

            //ADD CAREER OBJECTIVE
            PdfPTable table2=new PdfPTable(1);
            table2.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table2.getDefaultCell().setBorderColorBottom(RED);
            table2.getDefaultCell().setBorderWidth(3);
            table2.getDefaultCell().setPaddingBottom(10f);
            table2.getDefaultCell().setPaddingTop(10f);
//            table2.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
            table2.setWidthPercentage(100);
            table2.addCell(new Paragraph("CAREER OBJECTIVE",fontSize_10));
            doc.add(table2);

            //SET VALUE OF OBJECTIVE
            PdfPTable table21=new PdfPTable(1);
            table21.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table21.getDefaultCell().setPaddingBottom(10f);
            table21.getDefaultCell().setPaddingTop(10f);
            table21.setWidthPercentage(100);

            while (c1.moveToNext())
            {
                String obj=c1.getString(2);
                table21.addCell(new Paragraph(obj,fontSize_10));
            }
            doc.add(table21);

            //ADDING EXPERIENCE
            PdfPTable table3=new PdfPTable(1);
            table3.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table3.getDefaultCell().setBorderColorBottom(RED);
            table3.getDefaultCell().setBorderWidth(3);
            table3.getDefaultCell().setPaddingBottom(10f);
            table3.getDefaultCell().setPaddingTop(10f);
            table3.setWidthPercentage(100);
            table3.addCell(new Paragraph("EXPERIENCE",fontSize_10));
            doc.add(table3);

            //SET VALUES OF EXPERIENCE
            PdfPTable table31=new PdfPTable(1);
            table31.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table31.getDefaultCell().setPaddingTop(10f);
            table31.setWidthPercentage(100);

            while (c2.moveToNext())
            {
                String org=c2.getString(2);
                String designation=c2.getString(3);
                String duration=c2.getString(4);
                table31.addCell(new Paragraph(org,fontSize_20));
                table31.addCell(new Paragraph(designation,fontSize_10));
                table31.addCell(new Paragraph(duration,fontSize_10));
            }
            doc.add(table31);

            //ADDING EDUCATION
            PdfPTable table4=new PdfPTable(1);
            table4.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table4.getDefaultCell().setBorderColorBottom(RED);
            table4.getDefaultCell().setBorderWidth(3);
            table4.getDefaultCell().setPaddingBottom(10f);
            table4.getDefaultCell().setPaddingTop(10f);
            table4.setWidthPercentage(100);
            table4.addCell(new Paragraph("EDUCATION",fontSize_10));
            doc.add(table4);

            //SET VALUES OF EDUCATION
            PdfPTable table41=new PdfPTable(1);
            table41.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table41.getDefaultCell().setPaddingTop(10f);
            table41.setWidthPercentage(100);

            while (c3.moveToNext())
            {
                String course=c3.getString(2);
                String institute=c3.getString(3);
                String cgpa=c3.getString(4);
                String year=c3.getString(5);
                table41.addCell(new Paragraph(course,fontSize_20));
                table41.addCell(new Paragraph(institute,fontSize_10));
                table41.addCell(new Paragraph(cgpa,fontSize_10));
                table41.addCell(new Paragraph(year,fontSize_10));
            }
            doc.add(table41);

            //ADDING TECHNICAL SKILLS
            PdfPTable table5=new PdfPTable(1);
            table5.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table5.getDefaultCell().setBorderColorBottom(RED);
            table5.getDefaultCell().setBorderWidth(3);
            table5.getDefaultCell().setPaddingBottom(10f);
            table5.getDefaultCell().setPaddingTop(10f);
            table5.setWidthPercentage(100);
            table5.addCell(new Paragraph("TECHNICAL SKILLS",fontSize_10));
            doc.add(table5);

            //SET THE VALUES OF SKILLS
//            Font zapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 8);
//            Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);
//            List list = new List(List.UNORDERED);
//            list.setListSymbol(bullet);
//            list.setIndentationRight(10f);

            ZapfDingbatsList list1 =
                    new ZapfDingbatsList(108, 20);
            while (c4.moveToNext())
            {
                String skill=c4.getString(2);
                list1.add(new ListItem(new Paragraph(skill,fontSize_10)));
                //table51.addCell(new Paragraph(skill,fontSize_10));
            }
            doc.add(list1);

            //ADDING PROJECTS
            PdfPTable table6=new PdfPTable(1);
            table6.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table6.getDefaultCell().setBorderColorBottom(RED);
            table6.getDefaultCell().setBorderWidth(3);
            table6.getDefaultCell().setPaddingBottom(10f);
            table6.getDefaultCell().setPaddingTop(10f);
            table6.setWidthPercentage(100);
            table6.addCell(new Paragraph("PROJECTS",fontSize_10));
            doc.add(table6);

            //SET VALUES OF PROJECTS
            PdfPTable table61=new PdfPTable(1);
            table61.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table61.getDefaultCell().setPaddingTop(10f);
            table61.setWidthPercentage(100);

            while (c5.moveToNext())
            {
                String projectname=c5.getString(2);
                String projectdes=c5.getString(3);
                String projectduration=c5.getString(4);
                table61.addCell(new Paragraph(projectname,fontSize_20));
                table61.addCell(new Paragraph(projectdes,fontSize_10));
                table61.addCell(new Paragraph(projectduration,fontSize_10));
            }
            doc.add(table61);

            //ADDING  ACHIVEMENT
            PdfPTable table10=new PdfPTable(1);
            table10.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table10.getDefaultCell().setBorderColorBottom(RED);
            table10.getDefaultCell().setBorderWidth(3);
            table10.getDefaultCell().setPaddingBottom(10f);
            table10.getDefaultCell().setPaddingTop(10f);
            table10.setWidthPercentage(100);
            table10.addCell(new Paragraph("ACHIVEMENT",fontSize_10));
            doc.add(table10);

            //SET THE VALUES OF INTERESTS
            ZapfDingbatsList list91 =
                    new ZapfDingbatsList(108, 20);
            while (c9.moveToNext())
            {
                String achive=c9.getString(2);
                list91.add(new ListItem(new Paragraph(achive,fontSize_10)));
                //table51.addCell(new Paragraph(skill,fontSize_10));
            }
            doc.add(list91);

            //ADDING  INTERESTS
            PdfPTable table7=new PdfPTable(1);
            table7.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table7.getDefaultCell().setBorderColorBottom(RED);
            table7.getDefaultCell().setBorderWidth(3);
            table7.getDefaultCell().setPaddingBottom(10f);
            table7.getDefaultCell().setPaddingTop(10f);
            table7.setWidthPercentage(100);
            table7.addCell(new Paragraph("INTERESTS",fontSize_10));
            doc.add(table7);

            //SET THE VALUES OF INTERESTS
            ZapfDingbatsList list71 =
                    new ZapfDingbatsList(108, 20);
            while (c6.moveToNext())
            {
                String interest=c6.getString(2);
                list71.add(new ListItem(new Paragraph(interest,fontSize_10)));
                //table51.addCell(new Paragraph(skill,fontSize_10));
            }
            doc.add(list71);

            //ADDING STRENGTHS
            PdfPTable table8=new PdfPTable(1);
            table8.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table8.getDefaultCell().setBorderColorBottom(RED);
            table8.getDefaultCell().setBorderWidth(3);
            table8.getDefaultCell().setPaddingBottom(10f);
            table8.getDefaultCell().setPaddingTop(10f);
            table8.setWidthPercentage(100);
            table8.addCell(new Paragraph("STRENGTHS",fontSize_10));
            doc.add(table8);

            //SET THE VALUES OF STRENGTHS
            PdfPTable table81=new PdfPTable(1);
            table81.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table81.getDefaultCell().setPaddingBottom(10f);
            table81.getDefaultCell().setPaddingTop(10f);
            table81.setWidthPercentage(100);
            while (c7.moveToNext())
            {
                String strength=c7.getString(2);
                table81.addCell(new Paragraph(strength,fontSize_10));
                //table51.addCell(new Paragraph(skill,fontSize_10));
            }
            doc.add(table81);


            //ADDING PERSONAL PROFILE
            PdfPTable table9=new PdfPTable(1);
            table9.getDefaultCell().setBorder(Rectangle.BOTTOM);
            table9.getDefaultCell().setBorderColorBottom(RED);
            table9.getDefaultCell().setBorderWidth(3);
            table9.getDefaultCell().setPaddingBottom(10f);
            table9.getDefaultCell().setPaddingTop(10f);
            table9.setWidthPercentage(100);
            table9.addCell(new Paragraph("PERSONAL PROFILE",fontSize_10));
            doc.add(table9);

            //SET THE VALUES OF PERSONAL PROFILE
            PdfPTable table91=new PdfPTable(1);
            table91.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table91.getDefaultCell().setPaddingBottom(10f);
            table91.getDefaultCell().setPaddingTop(10f);
            table91.setWidthPercentage(100);
            while(c.moveToNext())
            {
                String dob=c.getString(7);
                String nationality=c.getString(8);
                table91.addCell(new Paragraph("Dtae of Birth :  "+dob,fontSize_10));
                table91.addCell(new Paragraph("Nationality :    "+nationality,fontSize_10));

            }
            while (c8.moveToNext())
            {
                String marital=c8.getString(2);
                String lang=c8.getString(3);
                String linkedin=c8.getString(4);
                String github=c8.getString(5);
                String website=c8.getString(6);

                table91.addCell(new Paragraph("Marital Status :  "+marital,fontSize_10));
                table91.addCell(new Paragraph("Known Language :  "+lang,fontSize_10));
                table91.addCell(new Paragraph("LinkedIn Profile :  "+linkedin,fontSize_10));
                table91.addCell(new Paragraph("GitHub :  "+github,fontSize_10));
                table91.addCell(new Paragraph("Website :  "+website,fontSize_10));

            }
            while(c7.moveToNext())
            {
                String hobby=null;
                hobby=hobby+","+c7.getString(3);
                table91.addCell(new Paragraph("Hobby :  "+hobby,fontSize_10));
            }
            doc.add(table91);



            //ADDING REFERENCE
//            PdfPTable table10=new PdfPTable(1);
//            table10.getDefaultCell().setBorder(Rectangle.BOTTOM);
//            table10.getDefaultCell().setBorderColorBottom(RED);
//            table10.getDefaultCell().setBorderWidth(3);
//            table10.getDefaultCell().setPaddingBottom(10f);
//            table10.getDefaultCell().setPaddingTop(10f);
//            table10.setWidthPercentage(100);
//            table10.addCell(new Paragraph("REFERENCE",fontSize_10));
//            doc.add(table10);

            //doc.addCreationDate();
            doc.close();
            Toast.makeText(this,"Resume.pdf\n is Saved to \n"+ nFilePath, Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {

            e.printStackTrace();
        }
    }

    private void viewPdfMethod() {

        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Resumes";
        File fileOpen = new File(path, "Resume.pdf");
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(path));
        pdfIntent.setDataAndType(Uri.fromFile(fileOpen),mimeType);
        pdfIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(pdfIntent, "Choose PDF view options"));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_WRITE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createPDFMethod();
                } else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_LONG).show();
                }

            }
            case STORAGE_READ_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewPdfMethod();
                } else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

}
