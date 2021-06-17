package com.jonoutomostore.home;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.bumptech.glide.Glide;
import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiProduk;
import com.jonoutomostore.db.AppDatabase;
import com.jonoutomostore.db.MyCart;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Constraint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProdukActivity extends AppCompatActivity implements View.OnClickListener {
    ApiProduk apiProduk;
    ImageView img;
    TextView nama, harga, stok, warna, ukuran, dilihat, terjual;
    TextView info, deks, ulasan;
    RecyclerView rvUlasan;
    ReviewAdapter reviewAdapter;
    Button btnAddCart;
    AppDatabase db;
    MyCart myCart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_detail_produl);
        img=findViewById(R.id.img);
        nama=findViewById(R.id.nama);
        harga=findViewById(R.id.harga);
        stok=findViewById(R.id.stok);
        warna=findViewById(R.id.warna);
        ukuran=findViewById(R.id.ukuran);
        dilihat=findViewById(R.id.dilihat);
        terjual=findViewById(R.id.terjual);
        info=findViewById(R.id.info);
        info.setOnClickListener(this);
        deks=findViewById(R.id.deskProduk);
        ulasan=findViewById(R.id.ulasan);
        ulasan.setOnClickListener(this);
        rvUlasan=findViewById(R.id.rvUlasan);
        rvUlasan.setLayoutManager(new LinearLayoutManager(this));
        rvUlasan.setHasFixedSize(true);
        reviewAdapter=new ReviewAdapter(this);
        rvUlasan.setAdapter(reviewAdapter);
        btnAddCart=findViewById(R.id.btnAddCart);
        btnAddCart.setOnClickListener(this);
        apiProduk=ApiClient.getAPI().create(ApiProduk.class);
        db = Room.databaseBuilder(this, AppDatabase.class, Constraint.DATABASE).allowMainThreadQueries().build();
        get();
    }

    private void get(){
        Call<DetailProduk> call=apiProduk.getDetailProduk(getIntent().getStringExtra("id_produk"));
        call.enqueue(new Callback<DetailProduk>() {
            @Override
            public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
                if(response.body().getProduk().getStatus().equals("200")){
                    Produk.Data product=response.body().getProduk().getData();

                    if(db.myCartDAO().existsCart(product.getId())){
                        btnAddCart.setVisibility(View.GONE);
                    }else{
                        btnAddCart.setVisibility(View.VISIBLE);
                    }

                    myCart=new MyCart();
                    myCart.setID(product.getId());
                    myCart.setNama(product.getName());
                    myCart.setQty(1);
                    myCart.setPrice(String.valueOf(product.getPrice().replace(".00", "")));
                    myCart.setImage(Constraint.PATH_IMG_PRODUK+product.getPicture_name());

                    Glide.with(getApplicationContext()).load(Constraint.PATH_IMG_PRODUK+product.getPicture_name()).into(img);
                    nama.setText(product.getName());
                    harga.setText(Constraint.RUPIAH(Double.parseDouble(product.getPrice())));
                    stok.setText("Tersedia "+product.getStok()+" "+product.getUnit());
                    terjual.setText("100000000 pcs");
                    deks.setText(product.getDeskripsi().replace("<br>", "").replace("</br>", ""));
                }else{
                    Toast.makeText(getApplicationContext(), "Produk Kosong", Toast.LENGTH_SHORT).show();
                }

                if(response.body().getReviews().getStatus().equals("200")){
                    reviewAdapter.Update(response.body().getReviews().getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Reviews Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailProduk> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info:
                info.setTypeface(null, Typeface.BOLD);
                info.setBackground(getResources().getDrawable(R.drawable.under_border_selected));
                deks.setVisibility(View.VISIBLE);
                ulasan.setTypeface(null, Typeface.NORMAL);
                ulasan.setBackground(null);
                rvUlasan.setVisibility(View.GONE);
                break;
            case R.id.ulasan:
                info.setTypeface(null, Typeface.NORMAL);
                info.setBackground(null);
                deks.setVisibility(View.GONE);
                ulasan.setTypeface(null, Typeface.BOLD);
                ulasan.setBackground(getResources().getDrawable(R.drawable.under_border_selected));
                rvUlasan.setVisibility(View.VISIBLE);
                break;
            case R.id.btnAddCart:
                db.myCartDAO().addCart(myCart);
                btnAddCart.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Tambah ke Keranjang Berhasil", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
