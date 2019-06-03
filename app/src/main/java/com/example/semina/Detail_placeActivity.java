package com.example.semina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_placeActivity extends AppCompatActivity {

    //layout
    TextView title, Address,phone,des;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin Địa Điểm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id
        Address = findViewById(R.id.Address);
        title = findViewById(R.id.title);
        des = findViewById(R.id.des);
        image = findViewById(R.id.image);
        //data

        final String name_place = getIntent().getStringExtra("name_place");
        final String address_place = getIntent().getStringExtra("address_place");
        final String image_place = getIntent().getStringExtra("image_place");
        final String des_place= getIntent().getStringExtra("des_place");
        Log.d("kiem tra hinh anh",image_place);
        Glide.with(this).load(image_place).into(image);
        title.setText(name_place);
        Address.setText(address_place);
        des.setText(des_place);

    }
}
