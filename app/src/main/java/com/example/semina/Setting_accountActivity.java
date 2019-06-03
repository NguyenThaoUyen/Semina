package com.example.semina;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.semina.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Setting_accountActivity extends AppCompatActivity {
    TextView  change_image,name,log_out,review;
    CircleImageView profile_image;
    private final int GALLERY_PICK = 1;
    // Storage Firebase
    private StorageReference ImageStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Account Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //find id


        //find id
        profile_image = findViewById(R.id.image);
        name = findViewById(R.id.name_avatar);
        change_image = findViewById(R.id.change_image);
        log_out = findViewById(R.id.log_out);
        review =findViewById(R.id.review);

        ImageStorage = FirebaseStorage.getInstance().getReference();

        // profile
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        assert currentUser != null;
        String current_Uid = currentUser.getUid();
        //firebase
        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_Uid);
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                name.setText(user.getUsername());
                //status.setText(user.getStatus());
                //show image
                if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.drawable.user);
                } else {
                    Glide.with(getBaseContext()).load(user.getImageURL()).into(profile_image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // set onclick


       // change_image.setOnClickListener(new View.OnClickListener() {
           // @Override
       //     public void onClick(View v) {
             //   String status_value = status.getText().toString();
            //    Intent intentstt = new Intent(SettingsActivity.this,  StatusActivity.class);
             //   intentstt.putExtra("status_value",status_value);
               // startActivity(intentstt);

            //}
       // }); // button change status

        // button change image
        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent,"SELECT IMAGE"), GALLERY_PICK);
            }
        });

        //log out link
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent b= new Intent(Setting_accountActivity.this,MainActivity.class);
                startActivity(b);
            }
        });
        //review link
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent review_intent= new Intent(Setting_accountActivity.this,Post_reviewActivity.class);
                startActivity(review_intent);

            }
        }

        );

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == GALLERY_PICK && resultCode ==RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                final String current_user_id = user.getUid();

                final StorageReference filepath = ImageStorage.child("profile_image").child(current_user_id + ".jpg");
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
                                        Toast.makeText(Setting_accountActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Setting_accountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }
}
