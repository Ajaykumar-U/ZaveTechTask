package com.ajay.registration.ui.home;

import com.ajay.registration.db.RegistrationDatabase;
import com.ajay.registration.db.User;
import com.ajay.registration.db.UserDao;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    private final UserDao userDao = RegistrationDatabase.getDb().userDao();

    @Override
    public void fetchUserFromDb() {
        new Thread(() -> {
            List<User> userList = userDao.getAllUser();
            if (view != null) view.showUser(userList);
        }).start();
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
