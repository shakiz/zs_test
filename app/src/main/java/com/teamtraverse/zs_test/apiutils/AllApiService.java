package com.teamtraverse.zs_test.apiutils;

import com.teamtraverse.zs_test.models.Login;
import com.teamtraverse.zs_test.models.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AllApiService {
    //Call for trending repositories
    @POST("user")
    Call<Message> login(@Body Login login);
}
