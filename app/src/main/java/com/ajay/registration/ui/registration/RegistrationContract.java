package com.ajay.registration.ui.registration;

public interface RegistrationContract {

    interface View {

        void showError(String errorMsg);

        void invalidName();

        void invalidMobileNumber();

        void invalidEmail();

        void invalidAddr();

        void launchHome();
    }

    interface Presenter {
        void attachView(RegistrationContract.View view);

        void detachView();

        void validateUser(String name, String mobileNo, String email, String gender, String addr);

    }
}

