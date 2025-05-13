package com.example.nextday_task.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nextday_task.Modal.ApiUser;
import com.example.nextday_task.Modal.FavoriteUser;
import com.example.nextday_task.Modal.UserResponse;
import com.example.nextday_task.Repository.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private UserRepository repository;
    private MutableLiveData<List<ApiUser>> apiUsers = new MutableLiveData<>();
    private LiveData<List<FavoriteUser>> favoriteUsers;

    public MainViewModel(UserRepository repository) {
        this.repository = repository;
        favoriteUsers = repository.getFavoriteUsers();
    }

    public void fetchApiUsers() {
        repository.getApiUsers().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiUsers.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // handle error
            }
        });
    }

    public LiveData<List<ApiUser>> getApiUsers() {
        return apiUsers;
    }

    public LiveData<List<FavoriteUser>> getFavoriteUsers() {
        return favoriteUsers;
    }

    public void addToFavorites(ApiUser user) {
        FavoriteUser fav = new FavoriteUser(user.getId(), user.getEmail(), user.getFirst_name(), user.getLast_name(), user.getAvatar());
        repository.addFavorite(fav);
    }

    public void removeFromFavorites(FavoriteUser user) {
        repository.removeFavorite(user);
    }
}
