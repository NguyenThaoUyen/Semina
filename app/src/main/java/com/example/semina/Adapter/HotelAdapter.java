package com.example.semina.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semina.ListhotelActivity;
import com.example.semina.Model.Hotel;
import com.example.semina.R;

import java.util.List;

public class HotelAdapter extends  RecyclerView.Adapter<HotelAdapter.HotelViewHolder>{
    private List<Hotel> hotelList;
    private Activity activity;
    private ItemClickListener itemClickListener;


    /**Contructor*/
    public HotelAdapter(Activity activity,List<Hotel> hotelList) {
        this.activity = activity;
        this.hotelList = hotelList;
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    /** Get layout */
    public HotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cardview,viewGroup,false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HotelAdapter.HotelViewHolder hotelViewHolder, int position) {
        /** Set Value*/
        final Hotel hotel = hotelList.get(position);



        hotelViewHolder.title.setText(hotel.getTitle());
        hotelViewHolder.content.setText(hotel.getContent());
        /**Sự kiện click vào item*/


        hotelViewHolder.itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(hotelViewHolder.getLayoutPosition());
                    }
                });




    }





    @Override
    public int getItemCount() {
      return hotelList.size();
    }

    /** Create ViewHolder*/

    public class HotelViewHolder  extends  RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView content;

        public HotelViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
