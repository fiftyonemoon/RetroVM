package com.fom.retrovm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fom.retrovm.activities.ProfileActivity;
import com.fom.retrovm.databinding.FragmentInfoBinding;
import com.fom.retrovm.models.User;
import com.fom.retrovm.viewmodel.ViewModel;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;
    private ProfileActivity activity;
    private ViewModel viewModel;
    private String username;

    public InfoFragment() {
    }

    public InfoFragment(String username) {
        this.username = username;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof ProfileActivity) {
            activity = (ProfileActivity) getActivity();
        }

        initUI();
        fetchUser();
    }

    private void initUI() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), observer);
    }

    private void fetchUser() {
        if (viewModel == null || username == null) return;
        viewModel.getUser(username);
    }

    final Observer<User> observer = user -> {
        if (user != null) {
            binding.setUser(user);
            if (activity != null && user.name != null) {
                ActionBar actionBar = activity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(user.name);
                }
            }
        }
    };
}
