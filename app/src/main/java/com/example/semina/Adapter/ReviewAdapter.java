package com.example.semina.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.semina.Model.Review;
import com.example.semina.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    private Context context;

    private ItemClickListener itemClickListener;
    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;

    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReviewAdapter.ReviewViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_review, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewAdapter.ReviewViewHolder holder, int position) {
        final Review review = reviews.get(position);
        TextView content = holder.review_content;
        content.setText(review.getReview_content());
        Glide.with(context).load(review.getImage_review()).centerCrop().into(holder.image_review);
        /**onclick*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder.getLayoutPosition());
            }
        });
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {


        public TextView review_content;
        public ImageView image_review;
       // public RelativeLayout item;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            review_content = itemView.findViewById(R.id.review_content);
            image_review=itemView.findViewById(R.id.image_review);
          //  item = itemView.findViewById(R.id.item);
        }
    }
    public void updateReview(List<Review> userReview){
        reviews = new ArrayList<>();
        reviews.addAll(userReview);
        notifyDataSetChanged();
    }

}