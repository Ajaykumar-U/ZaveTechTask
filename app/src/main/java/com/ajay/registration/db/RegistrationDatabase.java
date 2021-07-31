package com.ajay.registration.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ajay.registration.AppController;

@Database(entities = {User.class}, version = 1)
public abstract class RegistrationDatabase extends RoomDatabase {

    private static final String DB_NAME = "user_db";

    public abstract UserDao userDao();

    static RegistrationDatabase registrationDatabase;

    public static synchronized RegistrationDatabase getDb() {
        if (registrationDatabase == null) {
            registrationDatabase = Room.databaseBuilder(AppController.getContext(),
                    RegistrationDatabase.class,
                    DB_NAME)
                    .build();
        }
        return registrationDatabase;
    }
}
