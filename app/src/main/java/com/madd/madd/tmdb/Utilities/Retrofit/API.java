package com.madd.madd.tmdb.Utilities.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static Retrofit retrofit;
    private static String baseURL = "https://api.themoviedb.org/3/";

    public static Retrofit getAPI() {
        if( retrofit == null ) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
