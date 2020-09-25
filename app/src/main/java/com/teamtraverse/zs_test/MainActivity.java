package com.teamtraverse.zs_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.teamtraverse.zs_test.apiutils.AllApiService;
import com.teamtraverse.zs_test.apiutils.AllUrlClass;
import com.teamtraverse.zs_test.models.Login;
import com.teamtraverse.zs_test.models.Message;
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
                .baseUrl(allUrlClass.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        //Creating the logging interceptor
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        //Setting the level
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder= new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
        //Building the client
        apiInterface= retrofit.create(AllApiService.class);
        usernameStr=username.getText().toString();
        passwordStr=password.getText().toString();
        Login login=new Login(usernameStr,passwordStr);
        //Passing the body into login method
        Call<Message> call=apiInterface.login(login);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()){
                    Log.v("test::isSuccessful : ",""+response.body().getMessage());
                    if (response.body().getAction().equals("Login")){
                        Log.v("test::getAction : ",""+response.body().getAction());
                        if (response.body().getMessage().equals("Successful")){
                            Log.v("test::getMessage : ",""+response.body().getMessage());
                            Toast.makeText(LoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            List<UserDetails> userDetails = response.body().getDataArray();
                            progressDialog.dismiss();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else if (response.body().getMessage().equals("Failed")){
                            Log.v("test::getMessage : ",""+response.body().getMessage());
                            Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                    else if (response.body().getAction().equals("login")){
                        Toast.makeText(LoginActivity.this, "user already logged in", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                progressDialog.dismiss();
                Log.v("ERROR : ",""+t.getMessage());
            }
        });
    }
}