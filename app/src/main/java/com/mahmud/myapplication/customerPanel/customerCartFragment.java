package com.mahmud.myapplication.customerPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mahmud.myapplication.databinding.FragmentCustomerCartBinding;

public class customerCartFragment extends Fragment {


    private FragmentCustomerCartBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View v = inflater.inflate(R.layout.fragment_customer_cart, null);
//        getActivity().setTitle("Cart");

        binding = FragmentCustomerCartBinding.inflate(inflater, container, false);
        View v = binding.getRoot();


        return v;
    }
}
