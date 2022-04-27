package com.mahmud.myapplication.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmud.myapplication.R;
import com.mahmud.myapplication.model.MenuItem;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.viewHolder> {

    List< MenuItem > allmenuItems;

    public MenuAdapter(List< MenuItem > allRestaurants) {
        this.allmenuItems = allRestaurants;
    }

    @NonNull
    @Override
    public MenuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);

        return new MenuAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.viewHolder holder, int position) {

        MenuItem current = allmenuItems.get(position);

    }


    @Override
    public int getItemCount() {
        return allmenuItems.size();

    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView menuItemName, menuDescription, priceTextview;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            menuItemName = itemView.findViewById(R.id.itemNametextview);
            menuDescription = itemView.findViewById(R.id.itemDescriptiontextview);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Restaurant current = allmenuItems.get(getAdapterPosition());
//                    DataController.instance.getRestaurantInterface().onRestaurantClick(current);
//                }
//            });


            priceTextview = itemView.findViewById(R.id.itemPrice);



        }
    }
}
