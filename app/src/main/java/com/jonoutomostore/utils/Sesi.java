package com.jonoutomostore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jonoutomostore.profile.Profile;

public class Sesi {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Sesi(Context c){
        sharedPreferences=c.getSharedPreferences("Log", c.MODE_PRIVATE);
    }

    public void set(Profile.Data data){
        editor=sharedPreferences.edit();
        editor.putString("data", new Gson().toJson(data));
        editor.commit();
    }

    public Profile.Data get(){
        String data=sharedPreferences.getString("data", null);
        return new Gson().fromJson(data, Profile.Data.class);
    }

    public boolean valid(){
        return (sharedPreferences.getString("data", null) != null) ? true : false;
    }

    public void rmv(){
        editor=sharedPreferences.edit();
        editor.remove("data");
        editor.commit();
    }

}
