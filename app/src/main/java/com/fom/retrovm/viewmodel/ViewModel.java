package com.fom.retrovm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.fom.retrovm.models.User;
import com.fom.retrovm.retrofit.Repository;

import java.util.ArrayList;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class ViewModel extends AndroidViewModel {

    private final Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance();
    }

    public void getAllUsers() {
        if (repository != null) {
            repository.getAllUsers();
        }
    }

    public void getUser(String username) {
        if (repository != null) {
            repository.getUser(username);
        }
    }

    public void getFollowers(String username) {
        if (repository != null) {
            repository.getFollowers(username);
        }
    }

    public void getFollowing(String username) {
        if (repository != null) {
            repository.getFollowing(username);
        }
    }

    public MutableLiveData<User> getUserLiveData() {
        return repository.getUserLiveData();
    }

    public MutableLiveData<ArrayList<User>> getAllUsersLiveData() {
        return repository.getAllUsersLiveData();
    }

    public MutableLiveData<ArrayList<User>> getFollowersLiveData() {
        return repository.getFollowersLiveData();
    }

    public MutableLiveData<ArrayList<User>> getFollowingLiveData() {
        return repository.getFollowingLiveData();
    }
}
