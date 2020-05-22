package com.madd.madd.tmdb.data.http;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    @Provides
    public OkHttpClient provideHttpClient(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // 10MB
        int CACHE_SIZE = 10 * 1024 * 1024;
        return new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl,OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public TMDBApi provideTMDBApi(Context context){
        return provideRetrofit(TMDBApi.BASE_URL,provideHttpClient(context)).create(TMDBApi.class);
    }

}
