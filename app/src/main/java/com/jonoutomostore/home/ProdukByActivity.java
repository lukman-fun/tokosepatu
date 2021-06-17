package com.jonoutomostore.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiProduk;
import com.jonoutomostore.utils.ApiClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukByActivity extends AppCompatActivity implements ProdukAdapter.ProdukClick{
    RecyclerView rvProduk;
    ProdukAdapter produkAdapter;
    ApiProduk apiProduk;
    ProgressDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_by);
        rvProduk=findViewById(R.id.rvProduk);
        rvProduk.setLayoutManager(new GridLayoutManager(this, 2));
        rvProduk.setHasFixedSize(true);
        produkAdapter=new ProdukAdapter(this, ProdukByActivity.this);
        rvProduk.setAdapter(produkAdapter);
        apiProduk= ApiClient.getAPI().create(ApiProduk.class);
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading...");
        if(Integer.parseInt(getIntent().getStringExtra("type"))==0){
            //0=Search
            getSearch();
        }else{
            //1=Kategori
            getByKtg();
        }
    }

    private void getSearch(){
        pd.show();
        HashMap<String, Object> data=new HashMap<>();
        data.put("query", getIntent().getStringExtra("query"));

        Call<Produk> call=apiProduk.getSearch(data);
        call.enqueue(new Callback<Produk>() {
            @Override
            public void onResponse(Call<Produk> call, Response<Produk> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    produkAdapter.Update(response.body().getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Produk Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produk> call, Throwable t) {
                pd.show();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getByKtg(){
        pd.show();
        Call<Produk> call=apiProduk.getProdukByKtg(getIntent().getStringExtra("id_ktg"));
        call.enqueue(new Callback<Produk>() {
            @Override
            public void onResponse(Call<Produk> call, Response<Produk> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    produkAdapter.Update(response.body().getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Produk Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produk> call, Throwable t) {
                pd.show();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetailProduk(String id) {
        Intent i=new Intent(getApplicationContext(), DetailProdukActivity.class);
        i.putExtra("id_produk", id);
        startActivity(i);
    }

}
