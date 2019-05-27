package com.example.semina;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListhotelActivity extends AppCompatActivity {


    private static final String TAG ="ListhotelActivity";
    private ArrayList<Data> Hotels = new ArrayList<>();
    DataAdapter dataAdapter;
    RecyclerView rv_hotel;
    //database
    //FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listhotel);
        Log.d(TAG, "onCreate: Started.");

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Hotel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // recyclerview
        rv_hotel = findViewById(R.id.rv_hotel);
        rv_hotel.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_hotel.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this,Hotels);
        rv_hotel.setAdapter(dataAdapter);
        // read data

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Hotel");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Hotels.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Data hotels = data.getValue(Data.class);
                    Hotels.add(hotels);
                    dataAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

}
