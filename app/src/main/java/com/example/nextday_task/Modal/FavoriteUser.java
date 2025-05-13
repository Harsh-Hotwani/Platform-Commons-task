package com.example.nextday_task.Modal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_users")
public class FavoriteUser {

    @PrimaryKey
    private int id;

    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public FavoriteUser(int id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }
}
