package com.ajay.registration.ui.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.ajay.registration.R;
import com.ajay.registration.ui.home.HomeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

    private RegistrationContract.Presenter presenter = new RegistrationPresenter();
    private AutoCompleteTextView etRegistrationGender;
    private String[] gender;
    private TextInputEditText etRegistrationName,etRegistrationMobile,etRegistrationEmail,etRegistrationAddress;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initId();
        gender = getResources().getStringArray(R.array.gender);
        ArrayAdapter ad = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gender);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        etRegistrationGender.setAdapter(ad);
        presenter.attachView(this);

        btnSubmit.setOnClickListener((v)->{
            String name = etRegistrationName.getText().toString();
            String mobile = etRegistrationMobile.getText().toString();
            String genderDiff = etRegistrationGender.getText().toString();
            String email = etRegistrationEmail.getText().toString();
            String address = etRegistrationAddress.getText().toString();
            hideKeyBoard(v);
            presenter.validateUser(name,mobile,email,genderDiff,address);
        });
    }

    private void hideKeyBoard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }


    private void initId() {
        etRegistrationName = findViewById(R.id.etRegistrationName);
        etRegistrationMobile = findViewById(R.id.etRegistrationMobile);
        etRegistrationGender = findViewById(R.id.etRegistrationGender);
        etRegistrationEmail = findViewById(R.id.etRegistrationEmail);
        etRegistrationAddress = findViewById(R.id.etRegistrationAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    @Override
    public void showError(String errorMsg) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void invalidName() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout,"Required Name", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void invalidMobileNumber() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Required Mobile Number", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void invalidEmail() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Required E-mail", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void invalidAddr() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Required Address", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void launchHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

}