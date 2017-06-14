package com.example.rxbro.test1;

import java.util.Random;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rxbro on 6/14/2017.
 */

public interface RetrofitService {
    @GET("api/?results=20")
    Call<RandomAPI> getRandomUser();
}
