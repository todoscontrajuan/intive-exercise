package com.me.squad.intivefdvapp.service;

import com.me.squad.intivefdvapp.model.Results;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service for User Retrofit calls
 */
public interface UserService {
    // Get users basic information
    @GET("?results=52&inc=login,name,email,picture")
    Call<Results> getUsers();
}
