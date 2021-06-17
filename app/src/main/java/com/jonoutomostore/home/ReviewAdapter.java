package com.jonoutomostore.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    Context c;
    List<DetailProduk.Reviews.Data> data;

    public ReviewAdapter(Context c) {
        this.c = c;
    }

    public void Update(List<DetailProduk.Reviews.Data> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(c).inflate(R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        holder.nama.setText(data.get(position).getName());
        holder.rating.setRating(Float.parseFloat(data.get(position).getRating()));
        holder.tgl.setText("• "+data.get(position).getReview_date());
        holder.review.setText(data.get(position).getReview_text());
    }

    @Override
    public int getItemCount() {
        return data!=null ?data.size() : 0;
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        RatingBar rating;
        TextView tgl, review;
        public ReviewHolder(@NonNull View v) {
            super(v);
            nama=v.findViewById(R.id.namaCustomer);
            rating=v.findViewById(R.id.rating);
            tgl=v.findViewById(R.id.tgl);
            review=v.findViewById(R.id.review);
        }
    }
}
