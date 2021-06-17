package com.jonoutomostore.api;

import com.jonoutomostore.cart.City;
import com.jonoutomostore.cart.Provinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiOngkir {

    @GET("province")
    Call<List<Provinsi>> getProvinsi();

    @GET("city/{city_id}")
    Call<List<City>> getCity(@Path("city_id") String city_id);
}
