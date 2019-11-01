package com.example.weatherappuseretrofit;

import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static final String BASSE_URL="https://api.openweathermap.org/";
    private static Retrofit retrofit;


    static Retrofit getRetrofitInstance()
    {
        retrofit =new Retrofit.Builder()
                .baseUrl(BASSE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
