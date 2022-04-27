package com.mahmud.myapplication.customerPanel;

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
import com.mahmud.myapplication.Customer;
import com.mahmud.myapplication.Login;
import com.mahmud.myapplication.R;
import com.mahmud.myapplication.databinding.FragmentCustomerProfileBinding;

import java.util.HashMap;

public class CustomerProfileFragment extends Fragment {


    EditText name,mail;
    Button updateProfile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String UserName, UserEmail;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    private FragmentCustomerProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View v = inflater.inflate(R.layout.fragment_customer_profile, null);
//        getActivity().setTitle("Own Profile");


        binding = FragmentCustomerProfileBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        setHasOptionsMenu(true);





        name = v.findViewById(R.id.user_name);
        mail = v.findViewById(R.id.user_email);
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

    // methods

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

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


    public void getDataFromRealTimeDatabase(){
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = firebaseDatabase.getInstance().getReference("Customer").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Customer customer = snapshot.getValue(Customer.class);

                name.setText(customer.getUserName());
                mail.setText(customer.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void update(){
        UserName = name.getText().toString();
        UserEmail = mail.getText().toString();

        HashMap updateData = new HashMap();

        updateData.put("UserName", UserName);
        updateData.put("UserEmail", UserEmail);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");
        databaseReference.child(userid).updateChildren(updateData).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Your profile updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Sorry, your profile is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
