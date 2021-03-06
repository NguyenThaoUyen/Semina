package com.example.semina;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.semina.Adapter.ItemClickListener;
import com.example.semina.Adapter.ReviewAdapter;
import com.example.semina.Adapter.UsersAdapter;
import com.example.semina.Model.ListReview;
import com.example.semina.Model.Review;
import com.example.semina.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListpostActivity extends AppCompatActivity {
    RecyclerView recycler;
    ReviewAdapter reviewAdapter;
    ArrayList<Review>reviews = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    CircleImageView image_avatar;
    TextView name_avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpost);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trải Nghiệm Thú Vị ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //recyclerView
        image_avatar =findViewById(R.id.image_avatar);
        name_avatar=findViewById(R.id.name_avatar);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ListpostActivity.this);
        recycler.setLayoutManager(layoutManager);
        recycler.setLayoutManager(layoutManager);
        reviewAdapter =new ReviewAdapter(this,reviews );
        recycler.setAdapter(reviewAdapter);
        // read data
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Reviews");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String  review_content;
                reviews.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    review_content = data.child("review_content").getValue().toString();
                    Review reviews = new Review(review_content);
                    reviews.add(reviews);
                    reviewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}



