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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText user, email, password;
    Button register;


    //database
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find id
        user = findViewById(R.id.username);
        password = findViewById(R.id.textpass);
        email = findViewById(R.id.textemail);
        register = findViewById(R.id.btn_register);
        //firebase auth
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        //set on click button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_user = user.getText().toString();
                String txt_email = email.getText().toString();
                String txt_pass = password.getText().toString();

                if (TextUtils.isEmpty(txt_user) || (TextUtils.isEmpty(txt_email)) || (TextUtils.isEmpty(txt_pass))){
                    Toast.makeText(RegisterActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                } else if (txt_pass.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password must be a least 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_user, txt_email, txt_pass);
                }
            }
        });
    }
        private void register(final String username, String email, String password)
        {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = auth.getCurrentUser();
                                assert user != null;
                                String userid = user.getUid();
                                //push  database
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("id", userid);
                                hashMap.put("username", username);
                                hashMap.put("ImageURL", "default");
                                hashMap.put("status","Hi there, I'm using Aloha Chat App!!!");

                                databaseReference.child(userid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent a = new Intent(RegisterActivity.this, MainActivity.class);
                                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(a);
                                            finish();
                                        }
                                    }
                                });
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }

    }

