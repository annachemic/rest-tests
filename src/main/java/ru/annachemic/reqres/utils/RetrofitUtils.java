package ru.annachemic.reqres.utils;

import lombok.experimental.UtilityClass;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@UtilityClass
public class RetrofitUtils {
    public Retrofit getRetrofit() throws IOException {
        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
