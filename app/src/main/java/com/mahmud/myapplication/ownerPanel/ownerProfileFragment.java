package com.mahmud.myapplication.ownerPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahmud.myapplication.Login;
import com.mahmud.myapplication.Owner;
import com.mahmud.myapplication.R;

import java.util.HashMap;

public class ownerProfileFragment extends Fragment {

    EditText name,mail;
    Button updateProfile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String restaurantName, restaurantEmail;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_owner_profile, null);
        getActivity().setTitle("Own Profile");
        setHasOptionsMenu(true);


        name = v.findViewById(R.id.restaurant_name);
        mail = v.findViewById(R.id.restaurant_email);
        updateProfile = v.findViewById(R.id.SaveChanges);

        getDataFromRealTimeDatabase();


        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

    // Log out option in item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idd = item.getItemId();
        if(idd == R.id.LogOut){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    // showing current user data in "own profile" fragment
    public void getDataFromRealTimeDatabase(){

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = firebaseDatabase.getInstance().getReference("RestaurantOwner").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Owner owner = snapshot.getValue(Owner.class);

                name.setText(owner.getRestaurantName());
                mail.setText(owner.getRestaurantEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    // updateing profile
    public void update(){

        restaurantName = name.getText().toString();
        restaurantEmail = mail.getText().toString();

        HashMap updateData = new HashMap();

        updateData.put("restaurantName", restaurantName);
        updateData.put("restaurantEmail", restaurantEmail);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("RestaurantOwner");
        databaseReference.child(userid).updateChildren(updateData).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Your profile updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Sorry your profile is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }











//    private void getDataFromFirestore(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Restaurants")
//                .get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
//            @Override
//            public void onComplete(@NonNull Task< QuerySnapshot > task) {
//                if(task.isSuccessful()){
//                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                    name.setText(currentFirebaseUser.getUid().toString());
//                }
//            }
//        });
//    }

}
