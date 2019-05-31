package com.example.semina.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.semina.Model.User;
import com.example.semina.R;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private Context context;
    private ItemClickListener itemClickListener;

    public UserAdapter(List<User> users, Context context, ItemClickListener itemClickListener) {
        this.users = users;
        this.context = context;

    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener =itemClickListener;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserAdapter.UserViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_login_users, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
