package com.example.assignment__1.network;

import com.example.assignment__1.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserApi {
    @GET("{username}/repos")
    @Headers("Content-Type: application/json")
    Observable<List<User>> fetchRepoList(@Path(value = "username") String username);
}
