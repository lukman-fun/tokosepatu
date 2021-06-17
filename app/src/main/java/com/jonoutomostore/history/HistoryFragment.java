package com.jonoutomostore.history;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonoutomostore.R;
import com.jonoutomostore.api.ApiTransaksi;
import com.jonoutomostore.utils.ApiClient;
import com.jonoutomostore.utils.Sesi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryFragment extends Fragment implements HistoryAdapter.HistoryClick{
    RecyclerView rvHistory;
    ApiTransaksi apiTransaksi;
    HistoryAdapter historyAdapter;
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        rvHistory=v.findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setHasFixedSize(true);
        apiTransaksi= ApiClient.getAPI().create(ApiTransaksi.class);
        historyAdapter=new HistoryAdapter(getActivity(), HistoryFragment.this);
        rvHistory.setAdapter(historyAdapter);
        pd=new ProgressDialog(getActivity());
        pd.setMessage("Loading");
        pd.setCancelable(false);
        get(new Sesi(getActivity()).get().getId());
    }

    private void get(String id_users){
        pd.show();
        Call<History> call=apiTransaksi.getHistory(id_users);
        call.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                pd.cancel();
                if(response.body().getStatus().equals("200")){
                    historyAdapter.Update(response.body().getData());
                }else{
                    Toast.makeText(getActivity(), "History Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                pd.cancel();
                Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetail(String id_order) {
//        Toast.makeText(getActivity(), id_order, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getActivity(), DetailHistoryActivity.class);
        i.putExtra("id_order", id_order);
        startActivity(i);
    }

}
