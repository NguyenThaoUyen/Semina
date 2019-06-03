package com.example.semina;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.semina.Adapter.UsersAdapter;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView image_avatar;
    TextView name_avatar,text_login,text_register,log_out,review;
    private final int GALLERY_PICK = 1;
    // Storage Firebase
    private StorageReference ImageStorage;
  //  FirebaseUser firebaseUser;
    FirebaseUser currentUser;
    LinearLayout login,logout, register, review_text;
    ImageView change_image;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin Tài Khoản");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id
        login = findViewById(R.id.login);
        logout = findViewById(R.id.logout);
        register = findViewById(R.id.register);
        review_text = findViewById(R.id.review_text);
        image_avatar =findViewById(R.id.image_avatar);
        text_login=findViewById(R.id.text_login);
        text_register=findViewById(R.id.text_register);
        change_image =findViewById(R.id.change_image);
        log_out=findViewById(R.id.log_out);
        name_avatar=findViewById(R.id.name_avatar);
        review=findViewById(R.id.review);
        ImageStorage = FirebaseStorage.getInstance().getReference();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null){
            login.setVisibility(View.VISIBLE);
            login.setEnabled(true);
            register.setVisibility(View.VISIBLE);
            register.setEnabled(true);
            review_text.setVisibility(View.GONE);
            review_text.setEnabled(false);
            change_image.setVisibility(View.GONE);
            change_image.setEnabled(false);
            logout.setVisibility(View.GONE);
            logout.setEnabled(false);
            text_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent login =new Intent(ProfileActivity.this,LoginUsersActivity.class);
                    startActivity(login);
                }
            });
            text_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register =new Intent(ProfileActivity.this,RegisterActivity.class);
                    startActivity(register);
                }
            });
        }else{
            login.setVisibility(View.GONE);
            login.setEnabled(false);
            register.setVisibility(View.GONE);
            register.setEnabled(false);
            review_text.setVisibility(View.VISIBLE);
            review_text.setEnabled(true);
            change_image.setVisibility(View.VISIBLE);
            change_image.setEnabled(true);
            logout.setVisibility(View.VISIBLE);
            logout.setEnabled(true);

            //onclick
            log_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent l =new Intent(ProfileActivity.this,MainActivity.class);
                    startActivity(l);
                }
            });


            review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent review =new Intent(ProfileActivity.this,Post_reviewActivity.class);
                    startActivity(review);
                }
            });
            change_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent();
                    galleryIntent.setType("image/*");
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(galleryIntent,"SELECT IMAGE"), GALLERY_PICK);
                }
            });


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

        }
    }
    // gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                final String current_user_id = user.getUid();

                final StorageReference filepath = ImageStorage.child("image_avatar").child(current_user_id + ".jpg");
                UploadTask uploadTask = filepath.putFile(resultUri);
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        // Continue with the task to get the download URL
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            String download_Url = downloadUri.toString();
                            DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_id);
                            userDatabase.child("ImageURL").setValue(download_Url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ProfileActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }

        }


    }