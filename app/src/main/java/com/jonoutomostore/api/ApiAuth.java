package com.jonoutomostore.api;

import com.jonoutomostore.profile.Profile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiAuth {

    @POST("login")
    Call<Profile> login(@Body Profile.Data profile);

    @POST("daftar")
    Call<Response200> daftar(@Body Profile.Data profile);

    @GET("profile/{id_users}")
    Call<Profile> getProfile(@Path("id_users") String id_users);

    @POST("up_profile/{id_users}")
    Call<Response200> upProfile(@Body Profile.Data data, @Path("id_users") String id_users);
}
