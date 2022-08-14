package com.fom.retrovm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.fom.retrovm.adapters.UsersAdapter;
import com.fom.retrovm.databinding.ActivityMainBinding;
import com.fom.retrovm.models.User;
import com.fom.retrovm.viewmodel.ViewModel;

import java.util.ArrayList;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class UsersActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UsersAdapter adapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
        setRecyclerView();
        fetchUsers();
    }

    private void initUI() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAllUsersLiveData().observe(this, observer);
    }

    private void setRecyclerView() {
        adapter = new UsersAdapter();
        binding.rv.setAdapter(adapter);

        adapter.setOnClickListener((position, user) -> navigateToProfile(user));
    }

    private void fetchUsers() {
        if (viewModel == null) return;
        viewModel.getAllUsers();
    }

    void navigateToProfile(User user) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    final Observer<ArrayList<User>> observer = users -> {
        if (adapter != null && users != null) {
            adapter.setList(users);
        }
    };
}