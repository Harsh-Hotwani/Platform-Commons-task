package com.example.nextday_task;

import com.example.nextday_task.Modal.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/users?page=2")
    Call<UserResponse> getUsers();

}
