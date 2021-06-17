package com.jonoutomostore.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiAuth;
import com.jonoutomostore.api.Response200;
import com.jonoutomostore.profile.Profile;
import com.jonoutomostore.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nama, username, no_telp, email, alamat, password;
    Button btnDaftar;
    TextView toLogin;
    ApiAuth apiAuth;
    ProgressDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        nama=findViewById(R.id.nama);
        username=findViewById(R.id.username);
        no_telp=findViewById(R.id.no_telp);
        email=findViewById(R.id.email);
        alamat=findViewById(R.id.alamat);
        password=findViewById(R.id.password);
        btnDaftar=findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(this);
        toLogin=findViewById(R.id.toLogin);
        toLogin.setOnClickListener(this);
        apiAuth= ApiClient.getAPI().create(ApiAuth.class);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDaftar:
                daftar();
                break;
            case R.id.toLogin:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
    }

    private void daftar(){
        pd.show();
        Profile.Data data=new Profile.Data();
        data.setName(nama.getText().toString());
        data.setUsername(username.getText().toString());
        data.setPhoneNumber(no_telp.getText().toString());
        data.setEmail(email.getText().toString());
        data.setAddress(alamat.getText().toString());
        data.setPassword(password.getText().toString());

        Call<Response200> call=apiAuth.daftar(data);
        call.enqueue(new Callback<Response200>() {
            @Override
            public void onResponse(Call<Response200> call, Response<Response200> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response200> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
