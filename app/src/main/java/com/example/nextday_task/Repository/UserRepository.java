package com.example.nextday_task.Repository;

import androidx.lifecycle.LiveData;

import com.example.nextday_task.ApiService;
import com.example.nextday_task.DB.FavoriteUserDao;
import com.example.nextday_task.Modal.FavoriteUser;
import com.example.nextday_task.Modal.UserResponse;

import java.util.List;

import retrofit2.Call;

public class UserRepository {
    private ApiService apiService;
    private FavoriteUserDao favoriteUserDao;

    public UserRepository(ApiService apiService, FavoriteUserDao favoriteUserDao) {
        this.apiService = apiService;
        this.favoriteUserDao = favoriteUserDao;
    }

    public Call<UserResponse> getApiUsers() {
        return apiService.getUsers();
    }

    public void addFavorite(FavoriteUser user) {
        new Thread(() -> favoriteUserDao.insertUser(user)).start();
    }

    public void removeFavorite(FavoriteUser user) {
        new Thread(() -> favoriteUserDao.deleteUser(user)).start();
    }

    public LiveData<List<FavoriteUser>> getFavoriteUsers() {
        return favoriteUserDao.getAllFavoriteUsers();
    }
}
