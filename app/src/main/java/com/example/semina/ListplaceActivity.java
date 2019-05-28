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

public class ListplaceActivity extends AppCompatActivity {

    private ArrayList<Data> Places =new ArrayList<>();
        DataAdapter dataAdapter;
        RecyclerView rv_place;


        //firebase
           FirebaseDatabase firebaseDatabase;
           DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listplace);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Place");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recycleview
        rv_place =  findViewById(R.id.rv_place);
        rv_place.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        dataAdapter = new DataAdapter(this, Places);
        rv_place.setAdapter(dataAdapter);

        // firebase
       firebaseDatabase=  FirebaseDatabase.getInstance();
       databaseReference = firebaseDatabase.getReference("place");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String  name , address, image, des ;
                       Places.clear();
               for (DataSnapshot data : dataSnapshot.getChildren()){
                   address =data.child("address").getValue().toString();
                   name = data.child("name").getValue().toString();
                   image=data.child("image").getValue().toString();
                   des= data.child("des").getValue().toString();
                   Data places =new Data(image, address,name,des);
                   Places.add(places);
                   dataAdapter.notifyDataSetChanged();

           }}

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });




    }
}
