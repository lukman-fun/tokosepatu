package com.jonoutomostore.api;

import com.jonoutomostore.cart.Cart;
import com.jonoutomostore.history.DetailHistory;
import com.jonoutomostore.history.History;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiTransaksi {

    @POST("checkout")
    Call<Response200> postCheckout(@Body Cart cart);
    
    @GET("history/{id_users}")
    Call<History> getHistory(@Path("id_users") String id_users);

    @GET("detail_history/{id_order}")
    Call<DetailHistory> getDetailHistory(@Path("id_order") String id_order);

}
