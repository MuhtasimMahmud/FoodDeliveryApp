package com.mahmud.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView create_account;
    EditText mail, pass;
    Button login;
    String email,password;

    // Firebase authentication
    FirebaseAuth mAuth;


    // Firebase realtime database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Food Delivery");


        // initializing
        mail = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_password);
        create_account = findViewById(R.id.createAccount);
        login = findViewById(R.id.login_btn);


        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, chooseRole.class));
            }
        });
    }
    private void loginUser(){
        // getting the strings
        email = mail.getText().toString();
        password = pass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "log in successful", Toast.LENGTH_SHORT).show();


                    // eikhane realtime database theke user role check kore access dicchi

                    String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReference.child("RestaurantOwner").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Toast.makeText(Login.this, useridd, Toast.LENGTH_LONG).show();
                            if(snapshot.hasChild(useridd)){
                                startActivity(new Intent(Login.this, ownerOptions.class));
                            }else{
                                startActivity(new Intent(Login.this, customerOptions.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//                    startActivity(new Intent(Login.this, ownerOptions.class));

                }else{
                    Toast.makeText(Login.this, "login not successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}