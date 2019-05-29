package com.example.semina;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Model.Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListEventActivity extends AppCompatActivity {

    private ArrayList<Data> Events =new ArrayList<>();

    DataAdapter dataAdapter;
    RecyclerView rv_event;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // recyclerview
        rv_event = findViewById(R.id.rv_event);
        rv_event.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_event.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this,Events);
        rv_event.setAdapter(dataAdapter);
        // read data
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Event");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String add, image, des, phone, name;

                Events.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    name = data.child("name").getValue().toString();
                    add = data.child("Address").getValue().toString();
                    image = data.child("image").getValue().toString();
                    des = data.child("Des").getValue().toString();
                    phone=data.child("date").getValue().toString();
                    Data events = new Data(name,add,image,des,phone);
                    Events.add(events);
                    dataAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }

    }

