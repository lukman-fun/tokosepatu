package com.jonoutomostore.api;

import com.jonoutomostore.home.DetailProduk;
import com.jonoutomostore.home.Produk;
import com.jonoutomostore.kategori.Kategori;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiProduk {

    @GET("getHome")
    Call<Produk> getAllProduk();

    @GET("getKtg")
    Call<Kategori> getKtg();

    @GET("getDetailProduk/{id_produk}")
    Call<DetailProduk> getDetailProduk(@Path("id_produk") String id_produk);

    @POST("search")
    Call<Produk> getSearch(@Body HashMap<String, Object> data);

    @GET("produk_ktg/{id_ktg}")
    Call<Produk> getProdukByKtg(@Path("id_ktg") String id_ktg);
}
