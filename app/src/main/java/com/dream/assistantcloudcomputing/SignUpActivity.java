package com.dream.assistantcloudcomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SignUpActivity extends AppCompatActivity {
    EditText name,phone,email,pwd;
    Button signup;
    TextView signin;
    String str_name,str_phone,str_email,str_pwd;
    DB db = new DB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        initialise();
        SharedPreferences sharedpreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean("islogin",false)){
            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name = name.getText().toString();
                str_phone = phone.getText().toString();
                str_email = email.getText().toString();
                str_pwd = pwd.getText().toString();
                db.writeUser(str_name,str_email,str_phone,str_pwd);
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initialise() {
        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_mob);
        email = findViewById(R.id.et_email);
        pwd = findViewById(R.id.et_pwd);
        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.tv_signin);
    }


}