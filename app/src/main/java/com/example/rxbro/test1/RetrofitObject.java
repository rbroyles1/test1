package com.example.rxbro.test1;

/**
 * Created by rxbro on 6/12/2017.
 */

public interface RetrofitObject {
    @GET("api/RetrotfitAndroidObjectResponse")
    Call<RandomUser> getRandomUser();
}
