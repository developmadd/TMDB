package com.madd.madd.tmdb.HTTP;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TMDBModule {

    private static String BASE_URL = "https://api.themoviedb.org/3/";
    public static String TMDB_API_KEY = "5312d9a66aaa268a0e2aab662d455498";
    public static int TMDB_PAGINATE_STEP = 20;
    public static String TMDB_LANGUAGE = "es-MX";

    @Provides
    public Retrofit provideRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public TMDBApi provideTMDBApi(){
        return provideRetrofit(BASE_URL).create(TMDBApi.class);
    }

}
