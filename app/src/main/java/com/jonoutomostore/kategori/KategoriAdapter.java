package com.jonoutomostore.kategori;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;

import java.util.List;
import java.util.Random;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriHolder> {

    Context c;
    List<Kategori.Data> data;
    KategoriClick kategoriClick;
    public KategoriAdapter(Context c, KategoriClick kategoriClick){
        this.c=c;
        this.kategoriClick=kategoriClick;
    }

    public void Update(List<Kategori.Data> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KategoriHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KategoriHolder(LayoutInflater.from(c).inflate(R.layout.item_kategori, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriHolder holder, int position) {
        Random rand=new Random();
        int bgColor=Color.argb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        holder.cardView.setCardBackgroundColor(bgColor);
        holder.nama.setText(data.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kategoriClick.onKategori(data.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size() : 0;
    }

    public class KategoriHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nama;
        public KategoriHolder(@NonNull View v) {
            super(v);
            cardView=v.findViewById(R.id.cardKtg);
            nama=v.findViewById(R.id.namaKtg);
        }
    }

    public interface KategoriClick{
        void onKategori(String id);
    }
}
