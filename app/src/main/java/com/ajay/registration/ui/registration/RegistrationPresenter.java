package com.ajay.registration.ui.registration;

import android.text.TextUtils;

import com.ajay.registration.db.RegistrationDatabase;
import com.ajay.registration.db.User;
import com.ajay.registration.db.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view;

    private final UserDao userDao = RegistrationDatabase.getDb().userDao();

    @Override
    public void attachView(RegistrationContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void validateUser(String name, String mobileNo, String email, String gender, String addr) {
        new Thread(() -> {
            if (!isValidName(name)) {
                if (view != null) view.invalidName();
            } else if (!isValidMobile(mobileNo)) {
                if (view != null) view.invalidMobileNumber();
            } else if (!isValidEmail(email)) {
                if (view != null) view.invalidEmail();
            } else if (!isValidAddr(addr)) {
                if (view != null) view.invalidAddr();
            } else if (!isUserExist(email, mobileNo)) {
                if (view != null) view.showError("User Already Exist");
            } else {
                User user = new User(name, email, mobileNo, gender, addr);
                saveIntoDb(user);
            }
        }).start();
    }

    private void saveIntoDb(User user) {
        userDao.insertItem(user);
        if(view!=null) view.launchHome();
    }

    private boolean isUserExist(String email, String mobileNo) {
        User user = userDao.getUserByEmailOrMobileNo(email, mobileNo);
        return user == null;
    }

    /**
     * @param addr
     * @return
     */
    private boolean isValidAddr(String addr) {
        return !TextUtils.isEmpty(addr) || addr.length() > 3;
    }

    /**
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        String strPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @param mobileNo
     * @return
     */
    private boolean isValidMobile(String mobileNo) {
        return mobileNo.length() == 10;
    }


    /**
     * @param name
     * @return
     */
    boolean isValidName(String name) {
        return !TextUtils.isEmpty(name) || name.length() > 3;
    }

}
