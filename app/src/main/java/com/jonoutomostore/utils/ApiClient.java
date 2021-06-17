package com.jonoutomostore.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit=null;
    public static Retrofit getAPI(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Constraint.API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
