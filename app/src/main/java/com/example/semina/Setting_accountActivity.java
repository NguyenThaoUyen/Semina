package com.example.semina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Setting_accountActivity extends AppCompatActivity {
    TextView text_review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);
        text_review = findViewById(R.id.text_review);

        text_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login= new Intent(Setting_accountActivity.this,Post_reviewActivity.class);
                startActivity(intent_login);
            }
        });
    }
}
