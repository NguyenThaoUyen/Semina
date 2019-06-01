package com.example.semina.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.semina.ListpostActivity;
import com.example.semina.Model.User;
import com.example.semina.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<User> users;

    private Context context;
    private ItemClickListener itemClickListener;
    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;

    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UsersAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new UsersAdapter.UsersViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_review, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final UsersAdapter.UsersViewHolder holder, int position) {
        final User user = users.get(position);
        TextView name = holder.user_name;
        name.setText(user.getUsername());
        TextView status = holder.user_review;
        status.setText(user.getReview());
        CircleImageView image = holder.profile_image;
        if (user.getImageUrl().equals("default")) {
            image.setImageResource(R.drawable.user);
        } else {
            Glide.with(context).load(user.getImageUrl()).centerCrop().placeholder(R.drawable.default_avatar).into(image);
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder.getLayoutPosition());
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemLongClick(holder.getLayoutPosition());
            }
        });

    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        public TextView user_name;
        public CircleImageView profile_image;
        public TextView user_review;
        public RelativeLayout item;



        public UsersViewHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.image);
            user_review = itemView.findViewById(R.id.review);
            item = itemView.findViewById(R.id.item);
        }
    }

    public void updateList(List<User> userSearch){
        users = new ArrayList<>();
        users.addAll(userSearch);
        notifyDataSetChanged();
    }


}



