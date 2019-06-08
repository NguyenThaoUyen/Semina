package com.example.semina.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.semina.List_driverActivity;
import com.example.semina.Model.Driver;
import com.example.semina.R;


import java.util.ArrayList;
import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder>{
    private List<Driver> driverList;
    private Context context;


    public DriverAdapter(List<Driver> driverList, Context context) {
        this.driverList = driverList;
        this.context = context;
    }



    @NonNull
    @Override
    public DriverAdapter.DriverViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_driver,viewGroup,false);
        return new DriverAdapter.DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DriverAdapter.DriverViewHolder driverViewHolder, int position) {
        final Driver driver = driverList.get(position);
        driverViewHolder.title.setText(driver.getName());
        driverViewHolder.address.setText(driver.getAddress());
        driverViewHolder.phone.setText(driver.getPhone());

    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView phone;
        public TextView address;
        public RecyclerView item;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            phone=itemView.findViewById(R.id.phone);
            title = itemView.findViewById(R.id.title);
            address = itemView.findViewById(R.id.address);
            item= itemView.findViewById(R.id.item);

        }
    }
}
