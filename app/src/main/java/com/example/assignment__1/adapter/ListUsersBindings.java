package com.example.assignment__1.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ListUsersBindings {

    @BindingAdapter("avatar")
    public static void loadUserAvatar(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
