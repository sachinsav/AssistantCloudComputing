package com.dream.assistantcloudcomputing;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CountDownLatch;

public class DB {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
    private DatabaseReference recordRef = FirebaseDatabase.getInstance().getReference("records");
    public void writeUser(String name, String email, String phone,String pwd) {
        User user = new User(name, email,phone,pwd);
        userRef.child(phone).setValue(user);
    }

    public void writeRecord(String msg,String phone) {
        recordRef.child(phone).push().setValue(msg);
    }


    public void readUser(String phone) {

        mDatabase.child("users").child(phone).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase2", "Error getting data", task.getException());
                } else {
                    User user = task.getResult().getValue(User.class);
                }
            }
        });
    }
}