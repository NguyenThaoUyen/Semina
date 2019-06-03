package com.example.semina;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Post_reviewActivity extends AppCompatActivity {

    private EditText reviews;
    private DatabaseReference ReviewDatabase;
    FirebaseUser CurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        // database
        CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        assert CurrentUser != null;
        String current_Uid = CurrentUser.getUid();
        ReviewDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_Uid);
        // find id
        reviews = findViewById(R.id.review);
        Button btn_Save = findViewById(R.id.btn_post);

        //status value
        String review_value = getIntent().getStringExtra("review_value");
        reviews.setText(review_value);

        // set onclick button
        //button save changes
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String review = reviews.getText().toString();
                ReviewDatabase.child("review").setValue(review).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Progress.dismiss();
                            Intent intent  = new Intent(Post_reviewActivity.this, Setting_accountActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(Post_reviewActivity.this, "There was some error in saving changes. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
