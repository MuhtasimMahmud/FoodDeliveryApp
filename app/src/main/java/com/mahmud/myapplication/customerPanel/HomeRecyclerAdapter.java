package com.mahmud.myapplication.customerPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmud.myapplication.R;
import com.mahmud.myapplication.model.DataController;
import com.mahmud.myapplication.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.viewHolder> {

    List< Restaurant > allRestaurants;

    public HomeRecyclerAdapter(List< Restaurant > allRestaurants) {
        this.allRestaurants = allRestaurants;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Restaurant current = allRestaurants.get(position);
        holder.restaurantName.setText(current.getRestaurantName());
        holder.restaurantDescription.setText(current.getRestaurantDescription());

        Picasso.get().load(current.getRastaurantImgUrl()).fit().into(holder.restaurantImage);

    }


    @Override
    public int getItemCount() {
        return allRestaurants.size();

    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView restaurantImage;
        TextView restaurantName, restaurantDescription;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImageView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantDescription = itemView.findViewById(R.id.restaurantDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Restaurant current = allRestaurants.get(getAdapterPosition());
                    DataController.instance.getRestaurantInterface().onRestaurantClick(current);
                }
            });

        }
    }


}
