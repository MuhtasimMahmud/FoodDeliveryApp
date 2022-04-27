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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahmud.myapplication.model.MenuItem;
import com.mahmud.myapplication.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class ownerRegisterActivity extends AppCompatActivity {

    EditText mail, pass, res_name, confPass, img, description;
    Button register;

    String email,restaurantName,password,confirmPassword, restaurantImg, restaurantDescription;



    // Firebase authentication
    FirebaseAuth mAuth;


    // Firebase realtime database
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


//    HashMap<String, String> menu = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register);
        setTitle("Restaurant Owner Registration");


        // Initializing
        mail = findViewById(R.id.registration_restaurant_email);
        res_name = findViewById(R.id.registration_restaurant_name);
        img = findViewById(R.id.restaurant_image);
        description = findViewById(R.id.registration_restaurant_description);
        register = findViewById(R.id.register_owner);
        pass = findViewById(R.id.registration_restaurant_password);
        confPass = findViewById(R.id.confirm_registration_restaurant_password);



        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();


        // Firebase Realtime database
//        databaseReference = firebaseDatabase.getInstance().getReference();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }



    // methods outside main class

    private void createUser(){


        //getting the strings
        email = mail.getText().toString();
        restaurantName = res_name.getText().toString();
        restaurantImg = img.getText().toString();
        restaurantDescription = description.getText().toString();
        password = pass.getText().toString();
        confirmPassword = confPass.getText().toString();


//        menu.put(restaurantName, email);



        if(email.isEmpty() || password.isEmpty() || restaurantName.isEmpty() || confirmPassword.isEmpty()){
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
                        Toast.makeText(ownerRegisterActivity.this, "This email is already registered.", Toast.LENGTH_SHORT).show();
                    }else{

                        // jodi email ta already exist na kore taile eikhane registration kortesi

                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ownerRegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();


                                    // jodi authentication successfully hoy then eikhane realtime database e "restaurant owner" section data hisebe class upload dicchi

                                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                    Owner owner = new Owner(restaurantName, email, restaurantImg, restaurantDescription);
                                    firebaseDatabase = FirebaseDatabase.getInstance();
                                    databaseReference = firebaseDatabase.getReference("RestaurantOwner");
                                    databaseReference.child(userid).setValue(owner);

                                    List< MenuItem > menu = new ArrayList<>();

                                    for(int i =0; i<7; i++){
                                        menu.add(new MenuItem("Kacchi biriyani", "300", "1 plate kacchi with 2 piece mutton"));
                                    }

                                    CurrentMenu currentMenu = new CurrentMenu(menu);

                                    firebaseDatabase = FirebaseDatabase.getInstance();
                                    databaseReference = firebaseDatabase.getReference("RestaurantsMenu");
                                    databaseReference.child(userid).setValue(currentMenu);






                                    // sending data in firestore collection
                                    uploadDataToFirestore();


                                    // successfully registration korar pore Login activity te chole jabe
                                    startActivity(new Intent(ownerRegisterActivity.this, Login.class));

                                }else{
                                    Toast.makeText(ownerRegisterActivity.this, "Registration not successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
        }
    } // end of createUser


    private void uploadDataToFirestore(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Restaurants");

        Restaurant myrestaurant = new Restaurant();

        myrestaurant.setRestaurantName(restaurantName);
        myrestaurant.setRestaurantEmail(email);
        myrestaurant.setRestaurantDescription(restaurantDescription);
        myrestaurant.setRastaurantImgUrl(restaurantImg);

        List< MenuItem > myMenus = new ArrayList<>();

        for(int i =0; i<5; i++){
            myMenus.add(new MenuItem("Kacchi biriyani", "300", "1 plate kacchi with 2 piece mutton" ));
        }

        myrestaurant.setRestaurantMenulist(myMenus);


        // eikhane restaurant email ke keyword dhore firestore e data store kortesi

        db.collection("Restaurants").document(email).set(myrestaurant).addOnCompleteListener(new OnCompleteListener< Void >() {
            @Override
            public void onComplete(@NonNull Task< Void > task) {
                if(task.isSuccessful()){
//                    Toast.makeText(ownerRegisterActivity.this, "Restaurant data uploaded to collection", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(ownerRegisterActivity.this, "sorry couldn't upload to collection", Toast.LENGTH_SHORT).show();
                }
            }
        });


    } // end of uploadDataToFirestore


}