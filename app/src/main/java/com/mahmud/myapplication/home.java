package com.mahmud.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    private static final String TAG = "restaurants";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_recycler_item);

//        sendDataToFirestore();
//        getDataFromFirestore();
    }

/*    private void getDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurants")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot:task.getResult()){
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        Log.e(TAG, "onComplete: " + restaurant.getRestaurantName());
                        Toast.makeText(home.this, restaurant.getRestaurantName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void sendDataToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Restaurants");

        Restaurant myRestaurant = new Restaurant();
        myRestaurant.setRestaurantName("Hamider tong");
        myRestaurant.setRastaurantImgUrl("https://mir-s3-cdn-cf.behance.net/project_modules/fs/a4f01c82667439.5d24aafde4b5e.jpg");
        reference.add(myRestaurant).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    Toast.makeText(home.this, "Restaurant Data uploaded", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(home.this, "Sorry, not uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}