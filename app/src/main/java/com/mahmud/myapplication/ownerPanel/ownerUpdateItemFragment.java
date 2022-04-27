package com.mahmud.myapplication.ownerPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mahmud.myapplication.R;


public class ownerUpdateItemFragment extends Fragment {

    Button postDish;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_owner_update_item, null);
        getActivity().setTitle("Update item");


        postDish = (Button)v.findViewById(R.id.post_dish);
        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), owner_postDish.class));
            }
        });



        return v;
    }
}
