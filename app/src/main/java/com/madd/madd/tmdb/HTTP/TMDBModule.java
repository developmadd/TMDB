package com.madd.madd.tmdb.HTTP;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TMDBModule {

    private static String BASE_URL = "https://api.themoviedb.org/3/";

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
