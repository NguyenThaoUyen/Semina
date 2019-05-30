package com.example.semina;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_hotel, btn_place, btn_food, btn_profile;
//hotel
private ArrayList<Data> Events =new ArrayList<>();

    DataAdapter dataAdapter;
    RecyclerView rv_event;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Travel App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //find id
        btn_hotel = findViewById(R.id.btn_hotel);
        btn_food = findViewById(R.id.btn_food);
        btn_place = findViewById(R.id.btn_place);
        btn_profile = findViewById(R.id.btn_profile);

        //button hotel
        btn_hotel = findViewById(R.id.btn_hotel);
        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hotel_intent = new Intent (MainActivity.this, ListhotelActivity.class);
                startActivity(hotel_intent);
            }
        });
        // button food
        btn_food = findViewById(R.id.btn_food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food_intent = new Intent (MainActivity.this, ListFoodActivity.class);
                startActivity(food_intent);
            }
        });

        //button place
        btn_place = findViewById(R.id.btn_place);
        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent place_intent = new Intent (MainActivity.this, ListplaceActivity.class);
                startActivity(place_intent);
            }
        });
        // button profile
        btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile_intent = new Intent (MainActivity.this, ProfileActivity.class);
                startActivity(profile_intent);
            }
        });
        //rv

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
      }}

