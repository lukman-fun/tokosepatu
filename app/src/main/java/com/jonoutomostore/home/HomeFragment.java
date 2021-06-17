package com.jonoutomostore.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiProduk;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Constraint;


import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements ProdukAdapter.ProdukClick, View.OnClickListener {
    ApiProduk apiProduk;
    ViewPager banner;
    String[] dataBanner={Constraint.PATH_IMG_BANNER+"bgz2.jpg", Constraint.PATH_IMG_BANNER+"bg46.jpg"};
    RecyclerView rvProduk;
    ProdukAdapter produkAdapter;
    ProgressDialog pd;
    ImageView toSearch;
    EditText inputSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        banner=v.findViewById(R.id.banner);
        BannerAdapter bannerAdapter=new BannerAdapter(getActivity(), dataBanner);
        banner.setAdapter(bannerAdapter);
        rvProduk=v.findViewById(R.id.rvProduk);
        rvProduk.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvProduk.setHasFixedSize(true);
        produkAdapter=new ProdukAdapter(getActivity(), HomeFragment.this);
        rvProduk.setAdapter(produkAdapter);
        toSearch=v.findViewById(R.id.toSearch);
        toSearch.setOnClickListener(this);
        inputSearch=v.findViewById(R.id.inputSearch);
        apiProduk= ApiClient.getAPI().create(ApiProduk.class);
        pd=new ProgressDialog(getActivity());
        pd.setMessage("Loading");
        pd.setCancelable(false);
        get();
    }

    private void get(){
        pd.show();
        Call<Produk> call=apiProduk.getAllProduk();
        call.enqueue(new Callback<Produk>() {
            @Override
            public void onResponse(Call<Produk> call, Response<Produk> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    produkAdapter.Update(response.body().getData());
                }else{
                    Toast.makeText(getActivity(), "Produk Kosong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produk> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getActivity(),t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetailProduk(String id) {
//        Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getActivity(), DetailProdukActivity.class);
        i.putExtra("id_produk", id);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(getActivity(), ProdukByActivity.class);
        i.putExtra("type", "0");
        i.putExtra("query", inputSearch.getText().toString());
        startActivity(i);
    }
}
