package com.example.semina;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.semina.Adapter.ItemClickListener;
import com.example.semina.Adapter.UsersAdapter;
import com.example.semina.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListpostActivity extends AppCompatActivity {

    public static final String TAG = ListpostActivity.class.getSimpleName();
    ArrayList<User> users = new ArrayList<>();
    UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpost);


        RecyclerView all_review = findViewById(R.id.recycler);
        all_review.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        all_review.setLayoutManager(layoutManager);


        usersAdapter= new UsersAdapter(ListpostActivity.this,users);
        all_review.setAdapter(usersAdapter);

        usersAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }



            @Override
            public void onItemLongClick(int position) {
                Intent intent_profile = new Intent(ListpostActivity.this,ProfileActivity.class);
                intent_profile.putExtra("user_id", users.get(position).getId());
                startActivity(intent_profile);

            }
        });

        Log.d(TAG, usersAdapter.toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userDatabase = database.getReference("Users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        final String User_Uid = firebaseUser.getUid();
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                users.clear();
                String name, id, image, user_status, stt_on;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    name = data.child("username").getValue().toString();
                    id = data.getKey();
                    user_status = data.child("status").getValue().toString();
                    image = data.child("ImageURL").getValue().toString();
                    User user = new User( id, name, image, user_status);
                    if (!id.equals(User_Uid)){
                        users.add(user);
                        usersAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Có lỗi!" + databaseError.toString());
            }
        });



    }
}
