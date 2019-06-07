package com.example.semina;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Adapter.ItemClickListener;
import com.example.semina.Adapter.ReviewAdapter;
import com.example.semina.Adapter.UsersAdapter;
import com.example.semina.Model.Data;
import com.example.semina.Model.Review;
import com.example.semina.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity {
    private ArrayList<User> users = new ArrayList<>();
    UsersAdapter usersAdapter;
    //
    CircleImageView image_avatar;
    TextView name_avatar,xemthem, add;
    //list post
    private  ArrayList<Review>Reviews= new ArrayList<>();
    ReviewAdapter reviewAdapter;

    //
    Button btn_hotel, btn_place, btn_food, btn_profile;
    ImageButton btn_map;
    //event
    private ArrayList<Data> Events =new ArrayList<>();
        DataAdapter dataAdapter;
        RecyclerView rv_event;
        //firebase
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        // find id
        btn_map = findViewById(R.id.btn_map);
        //find id button
        btn_hotel = findViewById(R.id.btn_hotel);
        btn_food = findViewById(R.id.btn_food);
        btn_place = findViewById(R.id.btn_place);
        btn_profile = findViewById(R.id.btn_profile);
        xemthem=findViewById(R.id.xemthem);
        add=findViewById(R.id.add);
        //button hotel
        btn_hotel = findViewById(R.id.btn_hotel);
        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hotel_intent = new Intent(MainActivity.this, ListhotelActivity.class);
                startActivity(hotel_intent);
            }
        });
        // button food
        btn_food = findViewById(R.id.btn_food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food_intent = new Intent(MainActivity.this, ListFoodActivity.class);
                startActivity(food_intent);
            }
        });
        //button place
        btn_place = findViewById(R.id.btn_place);
        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent place_intent = new Intent(MainActivity.this, ListplaceActivity.class);
                startActivity(place_intent);
            }
        });
        // button profile
        btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile_intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile_intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alldriver = new Intent(MainActivity.this, List_driverActivity.class);
                startActivity(alldriver);
            }
        });
        xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all = new Intent(MainActivity.this, ListEventActivity.class);
                startActivity(all);
            }
        });
        //set recycler view cho event start
        rv_event = findViewById(R.id.rv_event);
        rv_event.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        rv_event.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(this, Events);
        rv_event.setAdapter(dataAdapter);
        // set onclick cho item_card_view_event
        dataAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent_event = new Intent(MainActivity.this, Detail_eventActivity.class);
                intent_event.putExtra("name_event", Events.get(position).getName());
                intent_event.putExtra("address_event", Events.get(position).getAddress());
                intent_event.putExtra("image_event", Events.get(position).getImage());
                intent_event.putExtra("des_event", Events.get(position).getDes());
                intent_event.putExtra("date_event", Events.get(position).getDate());
                startActivity(intent_event);
            }

            @Override
            public void onDeleteItemClick(int position) {

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
        // read data event
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Event");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name, add, image, des, phone;

                Events.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    add = data.child("Address").getValue().toString();
                    image = data.child("image").getValue().toString();
                    des = data.child("Des").getValue().toString();
                    phone = data.child("date").getValue().toString();
                    name = data.child("name").getValue().toString();

                    Data events = new Data(image, name, add, des, phone);
                    Events.add(events);
                    dataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        image_avatar = findViewById(R.id.image_avatar);
        name_avatar = findViewById(R.id.name_avatar);
        // read data user
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userDatabase = database.getReference("Users");
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                String name_avatar, image_avatar, id;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    name_avatar = data.child("username").getValue().toString();
                    id = data.getKey();
                    image_avatar = data.child("ImageURL").getValue().toString();
                    User user = new User(name_avatar, image_avatar, id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

             search(text);

                return true;
            }

          private void search(String text) { }
        });
        return true;
    }

}
   



