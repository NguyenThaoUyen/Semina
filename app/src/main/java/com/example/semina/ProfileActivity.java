package com.example.semina;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView text_login,text_register,text_review;

    FirebaseUser firebaseUser;


    @Override
    protected void onStart() {
        super.onStart();
        //check if user is null
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            Intent a = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(a);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id cho cac textview
        text_login = findViewById(R.id.text_login);
        text_register = findViewById(R.id.text_register);
        text_review = findViewById(R.id.text_review);

        //set on click cho cac text view
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login= new Intent(ProfileActivity.this,LoginUsersActivity.class);
                startActivity(intent_login);
            }
        });
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent intent_login= new Intent(ProfileActivity.this,RegisterActivity.class);
            startActivity(intent_login);
        }
    });
        text_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login= new Intent(ProfileActivity.this,Post_reviewActivity.class);
                startActivity(intent_login);
            }
        });



    }

    }





