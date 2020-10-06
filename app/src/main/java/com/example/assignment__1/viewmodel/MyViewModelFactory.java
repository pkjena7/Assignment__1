package com.example.assignment__1.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment__1.repository.UserRepository;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository repository;

    public MyViewModelFactory(UserRepository repository){
        this.repository = repository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(repository);
    }
}

