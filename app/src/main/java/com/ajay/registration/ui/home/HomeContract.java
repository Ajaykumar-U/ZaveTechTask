package com.ajay.registration.ui.home;

import com.ajay.registration.db.User;

import java.util.List;

public interface HomeContract {
    interface View {

        void showUser(List<User> users);
    }

    interface Presenter {
        void fetchUserFromDb();

        void attachView(HomeContract.View view);

        void detachView();
    }
}
