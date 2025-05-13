package com.example.nextday_task;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextday_task.Adapter.ApiUserAdapter;
import com.example.nextday_task.Adapter.FavoriteUserAdapter;
import com.example.nextday_task.DB.AppDatabase;
import com.example.nextday_task.Repository.UserRepository;
import com.example.nextday_task.ViewModel.MainViewModel;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView apiRecyclerView, favRecyclerView;
    private ApiUserAdapter apiAdapter;
    private FavoriteUserAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ApiService apiService = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);

        AppDatabase db = AppDatabase.getDatabase(this);
        UserRepository repository = new UserRepository(apiService, db.favoriteUserDao());

        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) new MainViewModel(repository);
            }
        }).get(MainViewModel.class);

        viewModel.fetchApiUsers();

        apiRecyclerView = findViewById(R.id.apiRecyclerView);
        favRecyclerView = findViewById(R.id.favRecyclerView);

        apiRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiAdapter = new ApiUserAdapter(new ArrayList<>(), this, user -> {
            viewModel.addToFavorites(user);
        });

        favAdapter = new FavoriteUserAdapter(new ArrayList<>(), this, favUser -> {
            viewModel.removeFromFavorites(favUser);
        });

        apiRecyclerView.setAdapter(apiAdapter);
        favRecyclerView.setAdapter(favAdapter);

        viewModel.getApiUsers().observe(this, users -> {
            if (users != null) {
                apiAdapter.updateData(users);
            }
        });

        viewModel.getFavoriteUsers().observe(this, favUsers -> {
            if (favUsers != null) {
                favAdapter.updateData(favUsers);
            }
        });
    }
}