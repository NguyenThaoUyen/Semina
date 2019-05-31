package com.example.semina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Post_reviewActivity extends AppCompatActivity {
            Button btn_post;
            EditText edit_review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id
        btn_post= findViewById(R.id.btn_post);
        edit_review=findViewById(R.id.edit_review);
        // on click
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
