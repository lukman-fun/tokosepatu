package com.jonoutomostore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jonoutomostore.cart.CartFragment;
import com.jonoutomostore.history.HistoryFragment;
import com.jonoutomostore.home.HomeFragment;
import com.jonoutomostore.kategori.KategoriFragment;
import com.jonoutomostore.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigasi=findViewById(R.id.navigasi);
        navigasi.setOnNavigationItemSelectedListener(this);
        setFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment f=null;
        switch (item.getItemId()){
            case R.id.home:
                f=new HomeFragment();
                break;
            case R.id.kategori:
                f=new KategoriFragment();
                break;
            case R.id.cart:
                f=new CartFragment();
                break;
            case R.id.history:
                f=new HistoryFragment();
                break;
            case R.id.profile:
                f=new ProfileFragment();
                break;
        }
        return setFragment(f);
    }

    private boolean setFragment(Fragment f){
        if(f!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, f)
                    .commit();
            return true;
        }
        return false;
    }

}