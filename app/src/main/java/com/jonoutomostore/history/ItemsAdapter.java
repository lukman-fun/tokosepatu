package com.jonoutomostore.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonoutomostore.R;
import com.jonoutomostore.utils.Constraint;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {
    Context c;
    List<DetailHistory.Items.Data> data;

    public ItemsAdapter(Context c) {
        this.c = c;
    }

    public void Update(List<DetailHistory.Items.Data> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsHolder(LayoutInflater.from(c).inflate(R.layout.item_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        Glide.with(c).load(Constraint.PATH_IMG_PRODUK+data.get(position).getPicture_name()).into(holder.img);
        holder.qty.setText(data.get(position).getOrder_qty());
        holder.hrg.setText(Constraint.RUPIAH(Double.parseDouble(data.get(position).getOrder_price().replace(".00", ""))));
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size() : 0;
    }

    public class ItemsHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView qty, hrg;
        public ItemsHolder(@NonNull View v) {
            super(v);
            img=v.findViewById(R.id.imgItems);
            qty=v.findViewById(R.id.qtyItems);
            hrg=v.findViewById(R.id.hrgItems);
        }
    }
}
