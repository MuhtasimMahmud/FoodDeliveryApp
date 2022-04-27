package com.mahmud.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customerRegistrationActivity extends AppCompatActivity {

    EditText mail, pass, uName, confPass;
    Button register;

    String email,password,name,confirmPassword;

    // Firebase authentication
    FirebaseAuth mAuth;


    // Firebase realtime database
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        setTitle("Customer Registration");

        // Initializing
        mail = findViewById(R.id.registration_customer_email);
        pass = findViewById(R.id.registration_customer_password);
        uName = findViewById(R.id.registration_customer_name);
        confPass = findViewById(R.id.confirm_registration_customer_password);
        register = findViewById(R.id.register_customer);



        //Firebase authentication
        mAuth = FirebaseAuth.getInstance();


        // Firebase Realtime database
        databaseReference = firebaseDatabase.getInstance().getReference();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }



    private void createUser(){
        //getting the strings
        email = mail.getText().toString();
        password = pass.getText().toString();
        name = uName.getText().toString();
        confirmPassword = confPass.getText().toString();


        if(email.isEmpty() || password.isEmpty() || name.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "Please enter all the information", Toast.LENGTH_SHORT).show();
        }else if(!password.matches(confirmPassword)){
            Toast.makeText(this, "Please confirm the same password", Toast.LENGTH_SHORT).show();
        }else{

            // eikhane check kortesi je already exist kore kina ei mail ta
            mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener< SignInMethodQueryResult >() {
                @Override
                public void onComplete(@NonNull Task< SignInMethodQueryResult > task) {
                    boolean check = task.getResult().getSignInMethods().isEmpty();
                    if(check == false){
                        Toast.makeText(customerRegistrationActivity.this, "This email is already registered.", Toast.LENGTH_SHORT).show();
                    }else{


                        // jodi email ta already exist na kore taile eikhane registration kortesi
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(customerRegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();



                                    // eikhane realtime database e "Customer" section e data upload dicchi
                                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    databaseReference.child("Customer").child(userid).child("UserName").setValue(name);
                                    databaseReference.child("Customer").child(userid).child("UserEmail").setValue(email);


                                    //Successfully register korar pore Login activity te chole jabe
                                    startActivity(new Intent(customerRegistrationActivity.this, Login.class));
                                }else{
                                    Toast.makeText(customerRegistrationActivity.this, "Registration not successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            });
        }
    } //end of createUser


}