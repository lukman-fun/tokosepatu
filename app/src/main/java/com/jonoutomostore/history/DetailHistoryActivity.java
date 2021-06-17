package com.jonoutomostore.history;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiTransaksi;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Constraint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryActivity extends AppCompatActivity {
    TextView no_order, tgl_order, item_order, hrg_order, pay_order, status_order;
    TextView nama_penerima, no_penerima, kurir, ongkir, alamat, kab, prov, kode_pos, catatan;
    TextView status_order_tindakan;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;
    LinearLayout ln_pay;
    int payMth;
    TextView tf_pay, tgl_pay, status_pay, pay_to, pay_from;
    Button transfer, cancel, delete;
    ApiTransaksi apiTransaksi;
    ProgressDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        no_order=findViewById(R.id.no_order);
        tgl_order=findViewById(R.id.tgl_order);
        item_order=findViewById(R.id.item_order);
        hrg_order=findViewById(R.id.hrg_order);
        pay_order=findViewById(R.id.metode_order);
        status_order=findViewById(R.id.status_order);

        rvItems=findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setHasFixedSize(true);
        itemsAdapter=new ItemsAdapter(this);
        rvItems.setAdapter(itemsAdapter);

        nama_penerima=findViewById(R.id.nama_penerima);
        no_penerima=findViewById(R.id.no_penerima);
        kurir=findViewById(R.id.kurir);
        ongkir=findViewById(R.id.ongkir);
        alamat=findViewById(R.id.alamat_penerima);
        kab=findViewById(R.id.kabupaten);
        prov=findViewById(R.id.provinsi);
        kode_pos=findViewById(R.id.kode_pos);
        catatan=findViewById(R.id.catatan);

        ln_pay=findViewById(R.id.lnPay);
        tf_pay=findViewById(R.id.tf_pay);
        tgl_pay=findViewById(R.id.tgl_pay);
        status_pay=findViewById(R.id.status_pay);
        pay_to=findViewById(R.id.tf_to);
        pay_from=findViewById(R.id.tf_from);

        transfer=findViewById(R.id.transfer);
        cancel=findViewById(R.id.cancel);
        delete=findViewById(R.id.delete);

        status_order_tindakan=findViewById(R.id.status_order_tindakan);

        apiTransaksi= ApiClient.getAPI().create(ApiTransaksi.class);

        pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.setCancelable(false);

        get();
    }

    private void get(){
        pd.show();
        Call<DetailHistory> call=apiTransaksi.getDetailHistory(getIntent().getStringExtra("id_order"));
        call.enqueue(new Callback<DetailHistory>() {
            @Override
            public void onResponse(Call<DetailHistory> call, Response<DetailHistory> response) {
                pd.cancel();
                //ORDERS
                if(response.body().getOrders().getStatus().equals("200")){
                    DetailHistory.Orders.Data orders=response.body().getOrders().getData();
                    no_order.setText(":   "+orders.getOrder_number());
                    tgl_order.setText(":   "+orders.getOrder_date());
                    item_order.setText(":   "+orders.getTotal_items()+" Barang");
                    hrg_order.setText(":   "+ Constraint.RUPIAH(Double.parseDouble(orders.getTotal_price().replace(".00", ""))));
                    pay_order.setText(":   "+orders.getPayment_method());
                    payMth=(orders.getPayment_method().equals("Transfer bank")) ? 1 : 2;
                    status_order.setText(":   "+orders.getOrder_status());
                }else{
                    Toast.makeText(getApplicationContext(), "Orders Kosong", Toast.LENGTH_SHORT).show();
                }

                if(response.body().getItems().getStatus().equals("200")){
//                    Toast.makeText(getApplicationContext(), response.body().getItems().getData().get(1).getName(), Toast.LENGTH_SHORT).show();
                    itemsAdapter.Update(response.body().getItems().getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Items Kosong", Toast.LENGTH_SHORT).show();
                }

                //PENERIMA
                if(response.body().getPenerima().getStatus().equals("200")){
                    DetailHistory.Penerima.Data penerima=response.body().getPenerima().getData();
                    nama_penerima.setText(penerima.getName());
                    no_penerima.setText(penerima.getPhone_number());
                    kurir.setText(penerima.getNama_kurir());
                    ongkir.setText(Constraint.RUPIAH(Double.parseDouble(penerima.getOngkir())));
                    alamat.setText(penerima.getAddress());
                    kab.setText(penerima.getKabupaten());
                    prov.setText(penerima.getProvince());
                    kode_pos.setText(penerima.getKode_pos());
                    catatan.setText(penerima.getNote());
                }else{
                    Toast.makeText(getApplicationContext(), "Penerima Kosong", Toast.LENGTH_SHORT).show();
                }

                //PEMBAYARAN
                if(payMth==1){
                    if(response.body().getBanks().getStatus().equals("200")){
                        DetailHistory.Banks.Data banks=response.body().getBanks().getData();
                        tf_pay.setText(Constraint.RUPIAH(Double.parseDouble(banks.getPayment_price().replace(".00", ""))));
                        tgl_pay.setText(banks.getPayment_date());
                        status_pay.setText(banks.getPayment_status());
                        pay_to.setText(banks.getTo());
                        pay_from.setText(banks.getFrom());
                        transfer.setVisibility(View.GONE);
                    }else{
//                        Toast.makeText(getApplicationContext(), "Payment BElum", Toast.LENGTH_SHORT).show();
                        transfer.setVisibility(View.VISIBLE);
                        ln_pay.setVisibility(View.GONE);
                    }
                }else{
                    ln_pay.setVisibility(View.GONE);
                }

                //TINDAKAN
                if(response.body().getTindakan().getStatus().equals("200")){
                    DetailHistory.Tindakan.Data tindakan=response.body().getTindakan().getData();
                    status_order_tindakan.setText(tindakan.getKet());
                    switch (tindakan.getAct()){
                        case "cancel":
                            cancel.setVisibility(View.VISIBLE);
                            break;
                        case "delete":
                            delete.setVisibility(View.VISIBLE);
                            break;
                        case "noact":
                            cancel.setVisibility(View.GONE);
                            delete.setVisibility(View.GONE);
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailHistory> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
