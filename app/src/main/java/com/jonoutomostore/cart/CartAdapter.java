package com.jonoutomostore.cart;

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
import com.jonoutomostore.db.MyCart;
import com.jonoutomostore.utils.Constraint;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    Context c;
    List<MyCart> data;
    onCartClick onclick;
    public CartAdapter(Context c, List<MyCart> data, onCartClick onclick) {
        this.c = c;
        this.data = data;
        this.onclick=onclick;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartHolder(LayoutInflater.from(c).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Glide.with(c).load(data.get(position).getImage()).into(holder.img);
        holder.nama.setText(data.get(position).getNama());
        holder.hrg.setText(Constraint.RUPIAH(Double.parseDouble(""+data.get(position).getPrice())));
        holder.qty.setText(""+data.get(position).getQty());
        holder.total.setText("Total : "+Constraint.RUPIAH(Double.parseDouble(String.valueOf(Integer.parseInt(data.get(position).getPrice())*data.get(position).getQty()))));
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onPlus(data.get(position), position);
            }
        });
        holder.min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onMin(data.get(position), position);
            }
        });
        holder.rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onRmv(data, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView nama, hrg, qty, total;
        ImageView plus, min, rmv;
        public CartHolder(@NonNull View v) {
            super(v);
            img=v.findViewById(R.id.imgCart);
            nama=v.findViewById(R.id.namaCart);
            hrg=v.findViewById(R.id.hrgCart);
            qty=v.findViewById(R.id.qtyCart);
            total=v.findViewById(R.id.totalCart);
            plus=v.findViewById(R.id.plusCart);
            min=v.findViewById(R.id.minCart);
            rmv=v.findViewById(R.id.rmvCart);
        }
    }

    public interface onCartClick{
        void onPlus(MyCart myCart, int posisi);
        void onMin(MyCart myCart, int posisi);
        void onRmv(List<MyCart> myCarts, int posisi);
    }
}
