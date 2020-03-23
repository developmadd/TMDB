package com.madd.madd.tmdb.Data.HTTP;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HTTPModule {


    @Provides
    public Retrofit provideRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public TMDBApi provideTMDBApi(){
        return provideRetrofit(TMDBApi.BASE_URL).create(TMDBApi.class);
    }

}
