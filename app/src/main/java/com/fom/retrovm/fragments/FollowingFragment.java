package com.fom.retrovm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fom.retrovm.activities.ProfileActivity;
import com.fom.retrovm.adapters.FragmentAdapter;
import com.fom.retrovm.databinding.LayoutFragmentBinding;
import com.fom.retrovm.models.User;
import com.fom.retrovm.viewmodel.ViewModel;

import java.util.ArrayList;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class FollowingFragment extends Fragment {

    private LayoutFragmentBinding binding;
    private ProfileActivity activity;
    private ViewModel viewModel;
    private FragmentAdapter adapter;
    private String username;

    public FollowingFragment() {
    }

    public FollowingFragment(String username) {
        this.username = username;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof ProfileActivity) {
            activity = (ProfileActivity) getActivity();
        }

        initUI();
        setRecyclerView();
        fetchFollowings();
    }

    private void initUI() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getFollowingLiveData().observe(getViewLifecycleOwner(), observer);
    }

    private void setRecyclerView() {
        adapter = new FragmentAdapter();
        binding.rv.setAdapter(adapter);

        adapter.setOnClickListener((position, user) -> navigateToProfile(user));
    }

    private void navigateToProfile(User user) {
        if (activity != null) {
            activity.loadUser(user);
        }
    }

    private void fetchFollowings() {
        if (viewModel != null && username != null) {
            viewModel.getFollowing(username);
        }
    }

    final Observer<ArrayList<User>> observer = following -> {
        if (adapter != null && following != null) {
            adapter.setList(following);
            binding.tvWarn.setVisibility(following.size() > 0 ? View.GONE : View.VISIBLE);
        }
    };
}
