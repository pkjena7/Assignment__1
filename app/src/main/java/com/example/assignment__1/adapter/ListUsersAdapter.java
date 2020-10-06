package com.example.assignment__1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment__1.databinding.ItemUserBinding;
import com.example.assignment__1.model.User;
import com.example.assignment__1.view.MyInterface;
import com.example.assignment__1.view.UserLisFragment;

import java.util.List;

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.UserHolder>{

    private UserLisFragment context;
    private List<User> users;

    public ListUsersAdapter(UserLisFragment context, List<User> users){
        this.context = context;
        this.users = users;

    }
    @NonNull
    @Override
    public ListUsersAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(context.getContext()),parent,false);
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUsersAdapter.UserHolder holder, int position) {
        User user = users.get(position);
        holder.itemView.setUser(user);
        holder.itemView.setListner((MyInterface) context);
        holder.itemView.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users==null?0:users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        public ItemUserBinding itemView;
        public UserHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}