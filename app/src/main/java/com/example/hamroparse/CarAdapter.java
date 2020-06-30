package com.example.hamroparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private ArrayList<CarModelClass> carModelClasses =new ArrayList<>();
    private Context context;

    public CarAdapter(ArrayList<CarModelClass> carModelClasses, Context context) {
        this.carModelClasses = carModelClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layoout inflatter to use list_items.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
        //json ko data UI ma set garnay
        holder.car_name.setText(carModelClasses.get(position).getName());
        holder.car_desc.setText(carModelClasses.get(position).getDesc());
        Picasso.get().load(carModelClasses.get(position).getImage()).into(holder.car_image);

    }

    @Override
    public int getItemCount() {
        return carModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView car_image;
        private TextView car_name;
        private TextView car_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            car_image = itemView.findViewById(R.id.car_image);
            car_name = itemView.findViewById(R.id.car_name);
            car_desc = itemView.findViewById(R.id.car_desc);
        }
    }
}
