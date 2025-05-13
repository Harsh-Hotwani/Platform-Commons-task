package com.example.nextday_task.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.nextday_task.Modal.FavoriteUser;

import java.util.List;


@Dao
public interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(FavoriteUser user);
    @Delete
    void deleteUser(FavoriteUser user);

    @Query("SELECT * FROM favorite_users")
    LiveData<List<FavoriteUser>> getAllFavoriteUsers();
}
