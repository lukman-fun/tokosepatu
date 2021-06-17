package com.jonoutomostore.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonoutomostore.R;
import com.jonoutomostore.utils.Constraint;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdulHolder> {
    Context c;
    List<Produk.Data> data;
    ProdukClick produkClick;

    public ProdukAdapter(Context c, ProdukClick produkClick) {
        this.c = c;
        this.produkClick = produkClick;
    }

    public void Update(List<Produk.Data> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProdulHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProdulHolder(LayoutInflater.from(c).inflate(R.layout.item_produk, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProdulHolder holder, int position) {
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produkClick.onDetailProduk(data.get(position).getId());
            }
        });
        Glide.with(c).load(Constraint.PATH_IMG_PRODUK +data.get(position).getPicture_name()).into(holder.img);
        holder.nama.setText(data.get(position).getName());
        holder.harga.setText(Constraint.RUPIAH(Double.parseDouble(data.get(position).price)));
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size() :0;
    }

    public class ProdulHolder extends RecyclerView.ViewHolder {
        LinearLayout ln;
        ImageView img;
        TextView nama, harga;
        public ProdulHolder(@NonNull View v) {
            super(v);
            ln=v.findViewById(R.id.lnProduk);
            img=v.findViewById(R.id.imgProduk);
            nama=v.findViewById(R.id.namaProduk);
            harga=v.findViewById(R.id.hrgProduk);
        }
    }

    public interface ProdukClick{
        void onDetailProduk(String id);
    }
}
