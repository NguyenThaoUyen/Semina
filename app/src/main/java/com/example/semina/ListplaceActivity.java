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
        rv_place.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this, Places);
        rv_place.setAdapter(dataAdapter);

        dataAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent_places = new Intent(ListplaceActivity.this, Detail_placeActivity.class);
                intent_places.putExtra("name_place", Places.get(position).getName());
                intent_places.putExtra("address_place", Places.get(position).getAddress());
                intent_places.putExtra("image_place", Places.get(position).getImage());
                intent_places.putExtra("des_place", Places.get(position).getDes());

                startActivity(intent_places);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

        // firebase
       firebaseDatabase=  FirebaseDatabase.getInstance();
       databaseReference = firebaseDatabase.getReference("place");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String add, image, des, name;
                       Places.clear();
               for (DataSnapshot data : dataSnapshot.getChildren()){
                   name = data.child("name").getValue().toString();
                   add = data.child("address").getValue().toString();
                   image = data.child("image").getValue().toString();
                   des = data.child("des").getValue().toString();
                   Data places = new Data(image,name,des,add);
                   Places.add(places);
                   dataAdapter.notifyDataSetChanged();
           }}

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


    }


}
