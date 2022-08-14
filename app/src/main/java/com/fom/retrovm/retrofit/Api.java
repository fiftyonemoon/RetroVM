package com.fom.retrovm.retrofit;

import com.fom.retrovm.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public interface Api {
    String BASE_URL = "https://api.github.com/";

    @GET("users")
    Call<ArrayList<User>> get();

    @GET("users/{user}")
    Call<User> get(@Path("user") String user);

    @GET("users/{user}/followers")
    Call<ArrayList<User>> getFollowers(@Path("user") String user);

    @GET("users/{user}/following")
    Call<ArrayList<User>> getFollowings(@Path("user") String user);
}
