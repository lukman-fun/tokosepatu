package com.jonoutomostore.profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiAuth;
import com.jonoutomostore.api.Response200;
import com.jonoutomostore.auth.LoginActivity;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Sesi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    EditText nama, username, no_telp, email, alamat, password;
    Button btnUpdate, btnLogout;
    ApiAuth apiAuth;
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        nama=v.findViewById(R.id.nama);
        username=v.findViewById(R.id.username);
        no_telp=v.findViewById(R.id.no_telp);
        email=v.findViewById(R.id.email);
        alamat=v.findViewById(R.id.alamat);
        password=v.findViewById(R.id.password);
        btnUpdate=v.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnLogout=v.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
        apiAuth= ApiClient.getAPI().create(ApiAuth.class);
        pd=new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.setMessage("Loading...");
        get();
    }

    private void get(){
        pd.show();
        Call<Profile> call=apiAuth.getProfile(new Sesi(getActivity()).get().getId());
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    Profile.Data profile=response.body().getData();
                    nama.setText(profile.getName());
                    username.setText(profile.getUsername());
                    no_telp.setText(profile.getPhoneNumber());
                    email.setText(profile.getEmail());
                    alamat.setText(profile.getAddress());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:
                pd.show();
                Profile.Data data=new Profile.Data();
                data.setName(nama.getText().toString());
                data.setUsername(username.getText().toString());
                data.setPhoneNumber(no_telp.getText().toString());
                data.setEmail(email.getText().toString());
                data.setAddress(alamat.getText().toString());
                data.setPassword(TextUtils.isEmpty(password.getText().toString()) ? "" : password.getText().toString());

                Call<Response200> call=apiAuth.upProfile(data, new Sesi(getActivity()).get().getId());
                call.enqueue(new Callback<Response200>() {
                    @Override
                    public void onResponse(Call<Response200> call, Response<Response200> response) {
                        pd.cancel();
                        if(response.body().getStatus().equals("200")){
                            Toast.makeText(getActivity(), "Update Profile Berhasi;", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response200> call, Throwable t) {
                        pd.cancel();
                        Toast.makeText(getActivity(), "Update Profile Berhasi;", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btnLogout:
                AlertDialog.Builder alertBuild=new AlertDialog.Builder(getActivity());
                alertBuild.setCancelable(false);
                alertBuild.setTitle("Log Out");
                alertBuild.setMessage("Apakah anda yakin ingin Logout dari akun ini?");
                alertBuild.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Sesi(getActivity()).rmv();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                        dialog.cancel();
                    }
                });
                alertBuild.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertBuild.show();
                break;
        }
    }

}
