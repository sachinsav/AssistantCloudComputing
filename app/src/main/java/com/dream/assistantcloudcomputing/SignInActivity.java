package com.dream.assistantcloudcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    EditText phone,pwd;
    String str_phone,str_pwd;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        SharedPreferences sharedpreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);

        phone = findViewById(R.id.let_mob);
        pwd = findViewById(R.id.let_pwd);
        login = findViewById(R.id.btn_signin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_phone = phone.getText().toString();
                str_pwd = pwd.getText().toString();
                if(str_phone.length()==0){
                    str_phone=" ";
                }
                FirebaseDatabase.getInstance().getReference().child("users").child(str_phone).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase2", "Error getting data", task.getException());
                        } else {
                            User user = task.getResult().getValue(User.class);
                            if (user != null && user.getPwd().equals(str_pwd)) {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean("islogin", true);
                                editor.putString("phone", str_phone);
                                editor.apply();
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putBoolean("islogin", false);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Phone No. or Password is wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}