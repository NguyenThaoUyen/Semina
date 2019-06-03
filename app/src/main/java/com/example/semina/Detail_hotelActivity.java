package com.example.semina;

import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.semina.Adapter.DataAdapter;
import com.example.semina.Model.Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Detail_hotelActivity extends AppCompatActivity {

    //layout
    TextView title, Address,phone,des;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin Khách Sạn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id
        Address = findViewById(R.id.Address);
        title = findViewById(R.id.title);
        phone = findViewById(R.id.phone);
        des = findViewById(R.id.des);
        image = findViewById(R.id.image);
        //data

        final String name_hotel = getIntent().getStringExtra("name_hotel");
        final String address_hotel = getIntent().getStringExtra("address_hotel");
        final String image_hotel = getIntent().getStringExtra("image_hotel");
        final String phone_hotel = getIntent().getStringExtra("phone_hotel");
        final String des_hotel = getIntent().getStringExtra("des_hotel");

        Log.d("kiem tra hinh anh",image_hotel);
        Glide.with(this).load(image_hotel).into(image);
        title.setText(name_hotel);
        Address.setText(address_hotel);
        phone.setText(phone_hotel);
        des.setText(des_hotel);

    }
        }














