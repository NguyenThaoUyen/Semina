package com.example.semina;


        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import com.example.semina.Adapter.DriverAdapter;
        import com.example.semina.Model.Driver;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class List_driverActivity extends AppCompatActivity {

    private ArrayList<Driver> Drives = new ArrayList<>();
    DriverAdapter driverAdapter;
    RecyclerView rv_driver;
    //database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_driver);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phương Tiện Di Chuyển ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // recyclerview
        rv_driver = findViewById(R.id.rv_driver);
        rv_driver.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_driver.setLayoutManager(linearLayoutManager);
        rv_driver.setAdapter(driverAdapter);
        // read data
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address, phone, name;
                Drives.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    name = data.child("name").getValue().toString();
                    address = data.child("address").getValue().toString();
                    phone = data.child("phone").getValue().toString();
                    Driver drivers = new Driver(name,phone,address);
                    Drives.add(drivers);
                    driverAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
