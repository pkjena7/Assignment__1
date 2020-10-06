package com.example.assignment__1.repository;

import com.example.assignment__1.model.User;
import com.example.assignment__1.network.UserApi;

import java.util.List;

import io.reactivex.Observable;

public class UserRepository {

    private UserApi api;
    public UserRepository(UserApi api){
        this.api = api;
    }

    public Observable<List<User>> fetchUserRepo(String userName){
        return api.fetchRepoList(userName);
    }
}
