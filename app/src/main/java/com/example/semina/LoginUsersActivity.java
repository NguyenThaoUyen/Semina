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

public class LoginUsersActivity extends AppCompatActivity {

    EditText ed_gmail, ed_password;
    Button btn_login;
    TextView forgot_password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_users);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //firebase
        auth = FirebaseAuth.getInstance();

        // find id
        ed_password= findViewById(R.id.ed_password);
        ed_gmail = findViewById(R.id.ed_gmail);
        btn_login = findViewById(R.id.btn_login);
        forgot_password= findViewById(R.id.forgot_password);



        // set on click button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_gmail = ed_gmail.getText().toString();
                String txt_password = ed_password.getText().toString();

                if ((TextUtils.isEmpty(txt_gmail)) || (TextUtils.isEmpty(txt_password))) {
                    Toast.makeText(LoginUsersActivity.this, " Yêu Cầu thất bại ", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(txt_gmail, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                        Intent intent = new Intent(LoginUsersActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginUsersActivity.this, "Tài khoảng không đúng", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
        //on  click forgot pass
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot_intent = new Intent(LoginUsersActivity.this,ResetPassActivity.class);
                startActivity(forgot_intent);
            }
        });
    }
}
