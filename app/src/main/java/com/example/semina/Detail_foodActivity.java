package com.example.semina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_foodActivity extends AppCompatActivity {


    //layout
    TextView title, Address,des;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin Món Ăn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id
        Address = findViewById(R.id.Address);
        title = findViewById(R.id.title);
        des = findViewById(R.id.des);
        image = findViewById(R.id.image);
        //data

        final String name_food = getIntent().getStringExtra("name_food");
        final String address_food = getIntent().getStringExtra("address_food");
        final String image_food = getIntent().getStringExtra("image_food");
        final String des_food = getIntent().getStringExtra("des_food");
        Log.d("kiem tra hinh anh",image_food);
        Glide.with(this).load(image_food).into(image);
        title.setText(name_food);
        Address.setText(address_food);
        des.setText(des_food);
    }
}
