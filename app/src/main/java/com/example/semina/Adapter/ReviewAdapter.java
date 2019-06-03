package com.example.semina.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.semina.Model.Review;
import com.example.semina.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    public class ReviewViewHolder {
        private List<Review> reviews;
        private Context context;

        public ReviewViewHolder(List<Review> reviews, Context context) {
            this.reviews = reviews;
            this.context = context;
        }


    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }



    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        public TextView name_avatar;
        public CircleImageView image_avatar;
        public TextView review;
        public RelativeLayout item;



        public ReviewViewHolder(View itemView) {
            super(itemView);
            name_avatar = itemView.findViewById(R.id.name_avatar);
            image_avatar = itemView.findViewById(R.id.image_avatar);
            review = itemView.findViewById(R.id.review);
            item = itemView.findViewById(R.id.item);
        }
    }


}
