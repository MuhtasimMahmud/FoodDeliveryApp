package com.mahmud.myapplication.customerPanel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mahmud.myapplication.R;
import com.mahmud.myapplication.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    HomeRecyclerAdapter adapter;

    List< Restaurant > myRestaurant = new ArrayList<>();

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.homeRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new HomeRecyclerAdapter(myRestaurant);
        recyclerView.setAdapter(adapter);

        GetDataFromFirestore();


        return root;


    }

    @Override
    public void onResume() {
        super.onResume();
        myRestaurant.clear();
    }

    private void GetDataFromFirestore(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurants").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task< QuerySnapshot > task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot:task.getResult()){
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        myRestaurant.add(restaurant);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
