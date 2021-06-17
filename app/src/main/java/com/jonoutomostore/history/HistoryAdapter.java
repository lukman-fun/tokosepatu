package com.jonoutomostore.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;
import com.jonoutomostore.utils.Constraint;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    Context c;
    List<History.Data> data;
    HistoryClick historyClick;
    public HistoryAdapter(Context c, HistoryClick historyClick){
        this.c=c;
        this.historyClick=historyClick;
    }

    public void Update(List<History.Data> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryHolder(LayoutInflater.from(c).inflate(R.layout.item_histroy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.no_order.setText("#"+data.get(position).getOrder_number());
        holder.tgl_order.setText("• "+data.get(position).getOrder_date());
        holder.pesanan.setText(data.get(position).getTotal_items()+" Barang");
        holder.total.setText(Constraint.RUPIAH(Double.parseDouble(data.get(position).getTotal_price().replace(".00", ""))));
        holder.pembayaran.setText(data.get(position).getPayment_method());
        holder.status.setText(data.get(position).getOrder_status());
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                historyClick.onDetail(data.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size() : 0;
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView no_order, tgl_order, pesanan, total, pembayaran, status;
        LinearLayout ln;
        public HistoryHolder(@NonNull View v) {
            super(v);
            no_order=v.findViewById(R.id.no_order);
            tgl_order=v.findViewById(R.id.tgl_order);
            pesanan=v.findViewById(R.id.pesanan_order);
            total=v.findViewById(R.id.total_order);
            pembayaran=v.findViewById(R.id.bayar_order);
            status=v.findViewById(R.id.status_order);
            ln=v.findViewById(R.id.lnOrder);
        }
    }

    public interface HistoryClick{
        void onDetail(String id_order);
    }
}
