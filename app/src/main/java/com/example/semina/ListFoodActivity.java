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

public class ListFoodActivity extends AppCompatActivity {

    private ArrayList<Data> Foods= new ArrayList<>();
         DataAdapter dataAdapter;
         RecyclerView rv_food;

    //firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Food");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recyclerview
        rv_food =findViewById(R.id.rv_food);
        rv_food.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_food.setLayoutManager(linearLayoutManager);
        dataAdapter =new DataAdapter(this,Foods);
        rv_food.setAdapter(dataAdapter);

        //read data
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Food");
        databaseReference.addValueEventListener(new ValueEventListener()

        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String image, des, add, name;

                Foods.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    add =data.child("Address").getValue().toString();
                    des =data.child("Des").getValue().toString();
                    image=data.child("image").getValue().toString();
                    name =data.child("name").getValue().toString();

                    Data foods =new Data(image, name,des, add);
                    Foods.add(foods);
                    dataAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
