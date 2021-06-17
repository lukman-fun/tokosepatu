package com.jonoutomostore.cart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.jonoutomostore.MainActivity;
import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiOngkir;
import com.jonoutomostore.api.ApiTransaksi;
import com.jonoutomostore.api.Response200;
import com.jonoutomostore.db.AppDatabase;
import com.jonoutomostore.db.MyCart;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Constraint;
import com.jonoutomostore.utils.Sesi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment implements CartAdapter.onCartClick, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RecyclerView rvCart;
    List<MyCart> dataCart;
    AppDatabase db;
    CartAdapter cartAdapter;
    EditText nama, no_telp, alamat, catatan;
    Spinner provinsi, kota, kurir;
    String[] provId, provName, cityId, cityName;
    ApiOngkir apiOngkir;
    ApiTransaksi apiTransaksi;
    RadioGroup payment;
    int payMethod=0;
    RadioButton tf;
    TextView total;
    Button btnPesan;
    int totals;
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        rvCart=v.findViewById(R.id.rvCart);
        rvCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCart.setHasFixedSize(true);
        db= Room.databaseBuilder(getActivity(), AppDatabase.class, Constraint.DATABASE).allowMainThreadQueries().build();db.myCartDAO().getAllCart();

        dataCart=db.myCartDAO().getAllCart();
        cartAdapter=new CartAdapter(getActivity(), dataCart, CartFragment.this);
        rvCart.setAdapter(cartAdapter);
        nama=v.findViewById(R.id.nama);
        no_telp=v.findViewById(R.id.no_telp);
        alamat=v.findViewById(R.id.alamat);
        catatan=v.findViewById(R.id.catatan);
        provinsi=v.findViewById(R.id.provinsi);
        kota=v.findViewById(R.id.kota);
        kurir=v.findViewById(R.id.kurir);
        payment=v.findViewById(R.id.pembayaran);
        tf=v.findViewById(R.id.bank);
        payment.setOnCheckedChangeListener(this);
        btnPesan=v.findViewById(R.id.btnPesan);
        btnPesan.setOnClickListener(this);
        total=v.findViewById(R.id.totalCart);
        total.setText("Rp 0");
        apiOngkir= ApiClient.getAPI().create(ApiOngkir.class);
        apiTransaksi = ApiClient.getAPI().create(ApiTransaksi.class);
        pd=new ProgressDialog(getActivity());
        pd.setMessage("Loading");
        pd.setCancelable(false);
        total();
        getProv();
    }

    private void total(){
        if(db.myCartDAO().getAllCart().size()>0){
            totals=0;
            for(MyCart iCart : db.myCartDAO().getAllCart()){
                totals+=(Integer.parseInt(iCart.getPrice())*iCart.getQty());
            }
            total.setText(Constraint.RUPIAH(Double.parseDouble(""+totals)));
            btnPesan.setVisibility(View.VISIBLE);
        }else{
            total.setText("Rp 0");
            btnPesan.setVisibility(View.GONE);
        }
    }

    private void getProv(){
        pd.show();
        Call<List<Provinsi>> callProv= apiOngkir.getProvinsi();
        callProv.enqueue(new Callback<List<Provinsi>>() {
            @Override
            public void onResponse(Call<List<Provinsi>> call, Response<List<Provinsi>> response) {
                pd.cancel();
                provId=new String[response.body().size()];
                provName=new String[response.body().size()];
                int i=0;
                for(Provinsi sprovinsi : response.body()){
                    provId[i]=sprovinsi.getProvince_id();
                    provName[i]=sprovinsi.getProvince();
                    i++;
                }
                if(i>0){
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, provName);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    provinsi.setAdapter(arrayAdapter);
                    getCity(provId[0]);
                    provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getCity(provId[position]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Provinsi>> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCity(String id){
        pd.show();
        Call<List<City>> callCity= apiOngkir.getCity(id);
        callCity.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                pd.cancel();
                cityId=new String[response.body().size()];
                cityName=new String[response.body().size()];
                int i=0;
                for(City scity : response.body()){
                    cityId[i]=scity.getCity_id();
                    cityName[i]=scity.getCity_name();
                    i++;
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cityName);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kota.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPlus(MyCart myCart, int posisi) {
        myCart.setQty(myCart.getQty()+1);
        db.myCartDAO().updateCart(myCart);
        cartAdapter.notifyDataSetChanged();
        total();
    }

    @Override
    public void onMin(MyCart myCart, int posisi) {
        if(myCart.getQty()>1){
            myCart.setQty(myCart.getQty()-1);
            db.myCartDAO().updateCart(myCart);
            cartAdapter.notifyDataSetChanged();
            total();
        }
    }

    @Override
    public void onRmv(List<MyCart> myCart, int posisi) {
        db.myCartDAO().rmvCArt(myCart.get(posisi));
        myCart.remove(posisi);
        cartAdapter.notifyDataSetChanged();
        total();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPesan:
                if(TextUtils.isEmpty(nama.getText().toString())||TextUtils.isEmpty(no_telp.getText().toString())
                ||TextUtils.isEmpty(alamat.getText().toString())||TextUtils.isEmpty(catatan.getText().toString())
                ||cityId==null){
                    Toast.makeText(getActivity(), "Lengkapi formulir pengiriman", Toast.LENGTH_SHORT).show();
                }else{
                    pd.show();
                    Cart cart=new Cart();
                    cart.setId_users(new Sesi(getActivity()).get().getId());
                    cart.setName(nama.getText().toString());
                    cart.setPhone_number(no_telp.getText().toString());
                    cart.setAddress(alamat.getText().toString());
                    cart.setNote(catatan.getText().toString());
                    cart.setTujuan(cityId[kota.getSelectedItemPosition()]);
                    cart.setKurir(kurir.getSelectedItem().toString());
                    cart.setPayment(""+(payMethod+1));
                    cart.setTotal_price(""+totals);
                    cart.setProduk(db.myCartDAO().getAllCart());

//                    JsonParser parser=new JsonParser();
//                    JsonObject jo=(JsonObject)parser.parse(new Gson().toJson(cart));
                    Call<Response200> call=apiTransaksi.postCheckout(cart);
                    call.enqueue(new Callback<Response200>() {
                        @Override
                        public void onResponse(Call<Response200> call, Response<Response200> response) {
                            pd.cancel();
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                db.myCartDAO().rmvAll();
                                dataCart.clear();
                                cartAdapter.notifyDataSetChanged();
                                nama.setText("");
                                no_telp.setText("");
                                alamat.setText("");
                                catatan.setText("");
                                getProv();
                                payment.clearCheck();
                                tf.setChecked(true);
                                total();
                            }
                        }

                        @Override
                        public void onFailure(Call<Response200> call, Throwable t) {
                            pd.cancel();
                            Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    Toast.makeText(getActivity(), jo.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        payMethod=payment.indexOfChild(group.findViewById(checkedId));
    }
}
