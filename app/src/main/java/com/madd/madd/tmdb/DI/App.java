package com.madd.madd.tmdb.DI;

import android.app.Application;

import com.madd.madd.tmdb.HTTP.TMDBModule;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .movieCatalogModule(new MovieCatalogModule())
                    .tMDBModule(new TMDBModule())
                    .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
