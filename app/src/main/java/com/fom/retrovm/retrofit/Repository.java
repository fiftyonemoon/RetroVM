package com.fom.retrovm.retrofit;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.fom.retrovm.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class Repository {

    private static Repository repository;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<User>> allUsersLiveData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<User>> followersLiveData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<User>> followingLiveData = new MutableLiveData<>();
    private final Api api;

    public static Repository getInstance() {
        return repository != null ? repository : (repository = new Repository());
    }

    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public void getAllUsers() {
        getAllUsersLiveData().setValue(null);
        api.get().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<User>> call, @NonNull Response<ArrayList<User>> response) {
                getAllUsersLiveData().postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<User>> call, @NonNull Throwable t) {
                t.printStackTrace();
                getAllUsersLiveData().postValue(null);
            }
        });
    }

    public void getUser(String username) {
        getUserLiveData().setValue(null);
        api.get(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                getUserLiveData().postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                t.printStackTrace();
                getUserLiveData().postValue(null);
            }
        });
    }

    public void getFollowers(String username) {
        getFollowersLiveData().setValue(null);
        api.getFollowers(username).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<User>> call, @NonNull Response<ArrayList<User>> response) {
                getFollowersLiveData().postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<User>> call, @NonNull Throwable t) {
                t.printStackTrace();
                getFollowersLiveData().postValue(null);
            }
        });
    }

    public void getFollowing(String username) {
        getFollowingLiveData().setValue(null);
        api.getFollowings(username).enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<User>> call, @NonNull Response<ArrayList<User>> response) {
                getFollowingLiveData().postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<User>> call, @NonNull Throwable t) {
                t.printStackTrace();
                getFollowingLiveData().postValue(null);
            }
        });
    }

    public MutableLiveData<ArrayList<User>> getAllUsersLiveData() {
        return allUsersLiveData;
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<ArrayList<User>> getFollowersLiveData() {
        return followersLiveData;
    }

    public MutableLiveData<ArrayList<User>> getFollowingLiveData() {
        return followingLiveData;
    }
}
