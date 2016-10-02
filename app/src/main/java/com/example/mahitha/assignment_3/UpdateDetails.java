package com.example.mahitha.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDetails extends AppCompatActivity {
    public final DatabaseHelper helper = new DatabaseHelper(this);

    public void onUpdateClick(View v){

        if(v.getId() == R.id.Bupdate)
        {
            EditText et_email = (EditText) findViewById(R.id.ET_email);
            EditText et_name = (EditText)findViewById(R.id.ET_name);
            EditText et_uname = (EditText)findViewById(R.id.ET_username);
            EditText et_pass = (EditText)findViewById(R.id.ET_password);

            String email, uname, password, name;
            email = et_email.getText().toString();
            uname = et_uname.getText().toString();
            password = et_pass.getText().toString();
            name = et_name.getText().toString();
            Contact contact = new Contact();
            contact.username = getIntent().getStringExtra("username");
            helper.deleteContact(contact);
            Contact c = new Contact();
            c.setName(name);
            c.setEmail(email);
            c.setUsername(uname);
            c.setPassword(password);

            helper.updateContact(c);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        Button b_update = (Button)findViewById(R.id.Bupdate);
        b_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onUpdateClick(v);
            }
        });
    }
}
