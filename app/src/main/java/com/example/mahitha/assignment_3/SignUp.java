package com.example.mahitha.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    public void onSignUpClick(View v){

        if(v.getId() == R.id.Bsignup)
        {
            EditText et_email = (EditText) findViewById(R.id.ETemail);
            EditText et_name = (EditText)findViewById(R.id.ETname);
            EditText et_uname = (EditText)findViewById(R.id.ETuname);
            EditText et_pass = (EditText)findViewById(R.id.ETpassword);
            EditText et_confirm = (EditText)findViewById(R.id.ETconfirm);

            String email, uname, password, name, p_confirm;
            email = et_email.getText().toString();
            uname = et_uname.getText().toString();
            password = et_pass.getText().toString();
            name = et_name.getText().toString();
            p_confirm = et_confirm.getText().toString();

            if(!password.equals(p_confirm)){
                Context context = getApplicationContext();
                CharSequence message = "Password does not match!";
                int duration = Toast.LENGTH_SHORT;
                final Toast toastBasic = Toast.makeText(context, message, duration);
                toastBasic.show();
            }
            else {
                Contact c = new Contact();
                c.setName(name);
                c.setEmail(email);
                c.setUsername(uname);
                c.setPassword(password);

                helper.insertContact(c);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button b_signup = (Button)findViewById(R.id.Bsignup);
        b_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onSignUpClick(v);
            }
        });

    }

}
