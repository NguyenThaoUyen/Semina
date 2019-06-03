package com.example.semina;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Adapter.ItemClickListener;
import com.example.semina.Model.Data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListhotelActivity extends AppCompatActivity {
    private ArrayList<Data> Hotels = new ArrayList<>();
    DataAdapter dataAdapter;
    RecyclerView rv_hotel;
        //database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listhotel);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Khách Sạn ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // recyclerview
        rv_hotel = findViewById(R.id.rv_hotel);
        rv_hotel.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_hotel.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this,Hotels);
        rv_hotel.setAdapter(dataAdapter);

        dataAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent_hotels = new Intent(ListhotelActivity.this, Detail_hotelActivity.class);
                intent_hotels.putExtra("name_hotel", Hotels.get(position).getName());
                intent_hotels.putExtra("address_hotel", Hotels.get(position).getAddress());
                intent_hotels.putExtra("image_hotel", Hotels.get(position).getImage());
                intent_hotels.putExtra("des_hotel", Hotels.get(position).getDes());
                intent_hotels.putExtra("phone_hotel", Hotels.get(position).getPhone());
                startActivity(intent_hotels);
            }

            @Override
            public void onDeleteItemClick(int position) {

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
        // read data
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Hotel");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String add, image, des, phone, name;
                Hotels.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    name = data.child("name").getValue().toString();
                    add = data.child("Address").getValue().toString();
                    image = data.child("image").getValue().toString();
                    des = data.child("Des").getValue().toString();
                    phone = data.child("phone").getValue().toString();

                    Data hotels = new Data(image,name,phone,des,add);
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
