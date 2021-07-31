package com.ajay.registration.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Insert
    void insertItem(User user);

    @Query("SELECT * FROM user Where email = :email or mobileNo = :mobileNo")
    User getUserByEmailOrMobileNo(String email, String mobileNo);

}
