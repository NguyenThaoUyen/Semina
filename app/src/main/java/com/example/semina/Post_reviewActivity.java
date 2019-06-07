package com.example.semina;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.semina.Adapter.ReviewAdapter;
import com.example.semina.Adapter.UsersAdapter;
import com.example.semina.Model.ListReview;
import com.example.semina.Model.Review;
import com.example.semina.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Post_reviewActivity extends AppCompatActivity {
    CircleImageView image_avatar;
    TextView name_avatar;
    EditText write_review;
    Button btn_post;
    //firebase
    UsersAdapter usersAdapter;
    ArrayList<User> mUsers = new ArrayList<>();
    DatabaseReference ReviewsReference;
    private ArrayList<ListReview> reviewList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trải nghiệm thú vị");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // find id
        write_review = findViewById(R.id.write_review);
        btn_post = findViewById(R.id.btn_post);
        name_avatar = findViewById(R.id.name_avatar);
        image_avatar = findViewById(R.id.image_avatar);
        // xử lí code
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userDatabase = database.getReference("Users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        final String User_Uid = firebaseUser.getUid();
        userDatabase.child(User_Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                name_avatar.setText(user.getUsername());
                //show image
                if (user.getImageURL().equals("default")) {
                    image_avatar.setImageResource(R.drawable.user);
                } else {
                    Glide.with(getBaseContext()).load(user.getImageURL()).into(image_avatar);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ReviewsReference = FirebaseDatabase.getInstance().getReference().child("Reviews");

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String revi = write_review.getText().toString();
                if (!revi.equals("")) {
                    HashMap<String, Object> reviewMap = new HashMap<>();
                    //push  database
                    reviewMap.put("review_content", revi);
                    reviewMap.put("image_review", "default");
                    ReviewsReference.push().setValue(reviewMap);
                    Intent return_intent= new Intent(Post_reviewActivity.this,ListpostActivity.class);
                    startActivity(return_intent);
                    finish();
                }

            }
        });


    }
    }



