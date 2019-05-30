package com.example.semina;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Adapter.ItemClickListener;
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
        dataAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent_event= new Intent(ListEventActivity.this, Detail_eventActivity.class);
                intent_event.putExtra("name_event", Events.get(position).getName());
                intent_event.putExtra("address_event", Events.get(position).getAddress());
                intent_event.putExtra("image_event", Events.get(position).getImage());
                intent_event.putExtra("des_event", Events.get(position).getDes());
                intent_event.putExtra("date_event", Events.get(position).getDate());
                startActivity(intent_event);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });







        // read data
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Event");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name,add, image, des, phone;

                Events.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){

                    add = data.child("Address").getValue().toString();
                    image = data.child("image").getValue().toString();
                    des = data.child("Des").getValue().toString();
                    phone = data.child("date").getValue().toString();
                    name = data.child("name").getValue().toString();

                    Data events = new Data(image,name,add,des,phone);
                    Events.add(events);
                    dataAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //onclick


        }

    }

