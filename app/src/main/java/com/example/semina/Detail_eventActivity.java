package com.example.semina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_eventActivity extends AppCompatActivity {

    //layout
    TextView title, Address,date,des;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin lễ Hội ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id
        Address = findViewById(R.id.Address);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        des = findViewById(R.id.des);
        image = findViewById(R.id.image);
        //data

        final String name_event = getIntent().getStringExtra("name_event");
        final String address_event = getIntent().getStringExtra("address_event");
        final String image_event = getIntent().getStringExtra("image_event");
        final String date_event = getIntent().getStringExtra("date_event");
        final String des_event = getIntent().getStringExtra("des_event");

        Log.d("kiem tra hinh anh",image_event);
        Glide.with(this).load(image_event).into(image);
        title.setText(name_event);
        Address.setText(address_event);
        date.setText(date_event);
        des.setText(des_event);
    }
}
