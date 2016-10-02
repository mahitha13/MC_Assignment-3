package com.example.mahitha.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText tv_username;
    private EditText tv_password;
    private Button b_login;
    private Button b_signup;
    private Button b_get;
    private Button b_save;
    private Button b_clear;

    private Intent intent;
    DatabaseHelper helper = new DatabaseHelper(this);
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Password = "passKey";
    private SharedPreferences sharedPreferences;

    /*public void Save(View view) {
        String n = tv_username.getText().toString();
        String p = tv_password.getText().toString();
        Editor editor = sharedPreferences.edit();
        editor.putString(Name, n);
        editor.putString(Password, p);
        editor.commit();
    }*/

    public void Clear(View view) {

        tv_username.setText("");
        tv_password.setText("");

    }
    public void Get(View view) {
        sharedPreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Name)) {
            tv_username.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(Password)) {
            tv_password.setText(sharedPreferences.getString(Password, ""));

        }
    }
    void init()
    {
        tv_username = (EditText) findViewById(R.id.TVusername);
        tv_password = (EditText) findViewById(R.id.TVpassword);

        b_login = (Button)findViewById(R.id.Blogin);
        b_signup = (Button)findViewById(R.id.Bsignup);
        b_get = (Button)findViewById(R.id.Bget);
        b_save = (Button)findViewById(R.id.Bsave);
        b_clear = (Button)findViewById(R.id.Bclear);
        sharedPreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        b_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String userName = tv_username.getText().toString();
                String password = tv_password.getText().toString();
                Editor editor = sharedPreferences.edit();
                editor.putString(Name, userName);
                editor.putString(Password, password);
                editor.commit();

                String pass = helper.searchPass(userName);

                if(pass.equals(password)){
                    Intent i = new Intent(getApplicationContext(),BlogHome.class);
                    i.putExtra("Username", userName);
                    startActivity(i);
                    }
                else{

                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                    }
            }
        });

        b_signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

        b_get.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Get(v);
            }
        });
        b_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String n = tv_username.getText().toString();
                String p = tv_password.getText().toString();
                Editor editor = sharedPreferences.edit();
                editor.putString(Name, n);
                editor.putString(Password, p);
                editor.commit();
            }
        });
        b_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Clear(v);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

}
