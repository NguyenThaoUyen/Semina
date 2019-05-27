package com.example.semina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

             Button btn_hotel, btn_place, btn_food, btn_profile;







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






    }


}
