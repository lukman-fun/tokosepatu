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

import com.jonoutomostore.MainActivity;
import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiAuth;
import com.jonoutomostore.profile.Profile;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Sesi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button btnLogin;
    TextView toDaftar;
    ApiAuth apiAuth;
    ProgressDialog pd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        toDaftar=findViewById(R.id.toDaftar);
        toDaftar.setOnClickListener(this);
        apiAuth= ApiClient.getAPI().create(ApiAuth.class);
        pd=new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        if(new Sesi(this).valid()){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.toDaftar:
                startActivity(new Intent(getApplicationContext(), DaftarActivity.class));
                finish();
                break;
        }
    }

    private void login(){
        pd.show();
        Profile.Data data=new Profile.Data();
        data.setUsername(username.getText().toString());
        data.setPassword(password.getText().toString());

        Call<Profile> call=apiAuth.login(data);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    new Sesi(getApplicationContext()).set(response.body().getData());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
