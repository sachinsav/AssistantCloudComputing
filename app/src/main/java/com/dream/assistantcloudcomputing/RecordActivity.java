package com.dream.assistantcloudcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    ListView l1;
    List<String> itemlist;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    ArrayAdapter<String> adapter;
    String str_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getSupportActionBar().setTitle("Records");

        sharedPreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        str_phone = sharedPreferences.getString("phone","000000000");

        l1 = findViewById(R.id.listview);
        itemlist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("records").child(str_phone);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    String record = item.getValue(String.class);
                    itemlist.add(record);
                }
                adapter = new ArrayAdapter<>(RecordActivity.this, android.R.layout.simple_list_item_1,itemlist);
                l1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}