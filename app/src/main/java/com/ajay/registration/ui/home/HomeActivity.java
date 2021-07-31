package com.ajay.registration.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ajay.registration.R;
import com.ajay.registration.db.User;
import com.ajay.registration.ui.home.adapter.HomeAdapter;

import java.util.List;

/**
 * <h>{@link HomeActivity} this is actbit</h>
 */
public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    final private HomeContract.Presenter presenter = new HomePresenter();

    private RecyclerView rvHomeUsers;

    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter.attachView(this);
        initId();
        init();
        presenter.fetchUserFromDb();
    }

    private void init() {
        homeAdapter = new HomeAdapter();
        rvHomeUsers.setAdapter(homeAdapter);
        rvHomeUsers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void initId() {
        rvHomeUsers = findViewById(R.id.rvHomeUsers);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showUser(List<User> users) {
        homeAdapter.addUserList(users);
    }
}