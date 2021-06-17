package com.jonoutomostore.kategori;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiProduk;
import com.jonoutomostore.home.ProdukByActivity;
import com.jonoutomostore.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriFragment extends Fragment implements KategoriAdapter.KategoriClick {
    RecyclerView rvKtg;
    KategoriAdapter adapter;
    ProgressDialog pd;
    ApiProduk apiProduk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kategori, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        rvKtg=v.findViewById(R.id.rvKtg);
        rvKtg.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvKtg.setHasFixedSize(true);
        adapter=new KategoriAdapter(getActivity(), KategoriFragment.this);
        rvKtg.setAdapter(adapter);
        pd=new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.setMessage("Loading...");
        apiProduk= ApiClient.getAPI().create(ApiProduk.class);
        get();
    }

    private void get(){
        pd.show();
        Call<Kategori> call=apiProduk.getKtg();
        call.enqueue(new Callback<Kategori>() {
            @Override
            public void onResponse(Call<Kategori> call, Response<Kategori> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    adapter.Update(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Kategori> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onKategori(String id) {
        Intent i=new Intent(getActivity(), ProdukByActivity.class);
        i.putExtra("type", "1");
        i.putExtra("id_ktg", id);
        startActivity(i);
    }

}
