package com.example.mahitha.assignment_3;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BlogHome extends AppCompatActivity {
    private TextView tv_welcome;
    public final DatabaseHelper helper = new DatabaseHelper(this);
    private Button b_delete;
    private Button b_logout;
    private Button b_update;
    private Button b_permBlog;
    private Button b_tempBlog;
    EditText textmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_home);

        tv_welcome = (TextView)findViewById(R.id.TVwelcome);
        final String u_name = getIntent().getStringExtra("Username");
        tv_welcome.setText("Welcome, "+ u_name + "!\n");
        b_permBlog = (Button)findViewById(R.id.Bpermblog);
        b_tempBlog = (Button)findViewById(R.id.Btempblog);
        b_delete = (Button)findViewById(R.id.Bdelete);
        b_logout = (Button)findViewById(R.id.Blogout);
        b_update = (Button)findViewById(R.id.Bupdate);
        textmsg=(EditText)findViewById(R.id.TVbody);

        final Contact contact = new Contact();
        contact.username = u_name;

        b_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                helper.deleteContact(contact);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        b_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        b_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdateDetails.class);
                i.putExtra("username",u_name);
                startActivity(i);
            }
        });
        b_permBlog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                writePublic(v);
            }
        });
        b_tempBlog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WriteBtn(v);
            }
        });
    }
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved in internal storage successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writePublic(View v){
        String content = "hello world";
        File file;
        FileOutputStream outputStream;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "MyCache");

            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
            Toast.makeText(getBaseContext(), "File saved in external storage successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
