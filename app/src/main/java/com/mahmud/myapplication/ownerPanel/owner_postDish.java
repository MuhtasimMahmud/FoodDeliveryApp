package com.mahmud.myapplication.ownerPanel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mahmud.myapplication.CurrentMenu;
import com.mahmud.myapplication.Owner;
import com.mahmud.myapplication.R;
import com.mahmud.myapplication.model.MenuItem;
import com.mahmud.myapplication.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class owner_postDish extends AppCompatActivity {

    ImageView imageButton;
    Button post_dish;
    EditText name,price;
    String item_name, item_price;
    MenuItem item;

    int x = 1;



    List<MenuItem> existingItems = new ArrayList<>();

    //Database
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth Fauth;
    StorageReference ref;

    String RestaurantName;
    String RestaurantEmail;
    String RestaurantImgURL;
    String RestaurantDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_post_dish);


        //Initializing
        name = (EditText)findViewById(R.id.dish_name);
        price = findViewById(R.id.dish_price);
        post_dish = findViewById(R.id.post);


        RestaurantName = "";
        RestaurantEmail = "";
        RestaurantDescription = "";
        RestaurantImgURL = "";



        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Fauth = FirebaseAuth.getInstance();


        post_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name = name.getText().toString();
                item_price = price.getText().toString();



                getDataFromRealTimeDatabase();

//                uploadDataToFirestore();

//                getDataFromFirestore();

            }
        });

    }






    public void getDataFromRealTimeDatabase(){

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // eikhane ami RestaurantOwner section theke restaurant er name,email,img,description nicchi

        databaseReference = firebaseDatabase.getInstance().getReference("RestaurantOwner").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Owner owner = snapshot.getValue(Owner.class);

                RestaurantName = owner.getRestaurantName();
                RestaurantEmail = owner.getRestaurantEmail();
                RestaurantImgURL = owner.getRastaurantImgUrl();
                RestaurantDescription = owner.getRestaurantDescription();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // eikhane ami RestaurantMenu section theke restaurant er menuItem er je hashmap ta oita nicchi

        databaseReference = firebaseDatabase.getInstance().getReference("RestaurantsMenu").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CurrentMenu currentMenu = snapshot.getValue(CurrentMenu.class);

                existingItems = currentMenu.getItems();
//                existingItems.add(new MenuItem(item_name, item_price));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void uploadDataToFirestore(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Restaurants");

        Restaurant myrestaurant = new Restaurant();

        myrestaurant.setRestaurantName(RestaurantName);
        myrestaurant.setRestaurantEmail(RestaurantEmail);
        myrestaurant.setRestaurantDescription(RestaurantDescription);
        myrestaurant.setRastaurantImgUrl(RestaurantImgURL);
        myrestaurant.setRestaurantMenulist(existingItems);


        // eikhane restaurant email ke keyword dhore firestore e data store kortesi

        db.collection("Restaurants").document(RestaurantEmail).set(myrestaurant).addOnCompleteListener(new OnCompleteListener< Void >() {
            @Override
            public void onComplete(@NonNull Task< Void > task) {
                if(task.isSuccessful()){

                }else{

                }
            }
        });
    }




}


