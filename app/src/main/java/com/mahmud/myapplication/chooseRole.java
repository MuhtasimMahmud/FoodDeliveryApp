package com.mahmud.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class chooseRole extends AppCompatActivity {

    Button customer, owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        setTitle("Choose Role");


        initialize();

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(chooseRole.this, ownerRegisterActivity.class));
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(chooseRole.this, customerRegistrationActivity.class));
            }
        });

    }

    public void initialize(){
        owner = findViewById(R.id.reg_as_owner);
        customer = findViewById(R.id.reg_as_customer);
    }
}