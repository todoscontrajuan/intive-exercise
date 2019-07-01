package com.me.squad.intivefdvapp.service;

import com.me.squad.intivefdvapp.model.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service for User Retrofit calls
 */
public interface UserService {
    // Get users basic information
    // Always using the same seed so we can get the same result set to paginate
    @GET("?results=52&inc=login,name,email,picture&seed=foobar")
    Call<Results> getUsers(@Query("page") int page);
}
