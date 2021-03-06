package com.teamtraverse.zs_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.teamtraverse.zs_test.adapter.RecyclerAdapterUserList;
import com.teamtraverse.zs_test.apiutils.AllApiService;
import com.teamtraverse.zs_test.apiutils.AllUrlClass;
import com.teamtraverse.zs_test.models.DataModel;
import com.teamtraverse.zs_test.models.Login;
import com.teamtraverse.zs_test.models.Message;
import com.teamtraverse.zs_test.models.Results;
import com.teamtraverse.zs_test.models.UserDetails;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit = null;
    private AllUrlClass allUrlClass;
    private AllApiService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allUrlClass = new AllUrlClass();

        getData();
    }

    public void getData(){
        //Creating a progress dialog which will appear when an user will try to login
        final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Data..Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        //Building the retrofit
        retrofit=new Retrofit.Builder()
                .baseUrl(allUrlClass.LIST_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        //Creating the logging interceptor
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        //Setting the level
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder= new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
        //Building the client
        apiInterface= retrofit.create(AllApiService.class);
        //Passing the body into login method
        Call<Results> call=apiInterface.getDataLists(allUrlClass.LIST_URL);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (response.isSuccessful()){
                    if (response.body().getResources() != null){
                        List<DataModel> resultsList = response.body().getResources();
                        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        RecyclerAdapterUserList recyclerAdapterUserList = new RecyclerAdapterUserList(resultsList,MainActivity.this);
                        recyclerView.setAdapter(recyclerAdapterUserList);
                        recyclerAdapterUserList.notifyDataSetChanged();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                progressDialog.dismiss();
                Log.v("ERROR : ",""+t.getMessage());
            }
        });
    }
}