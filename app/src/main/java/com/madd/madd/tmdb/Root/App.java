package com.madd.madd.tmdb.Root;

import android.app.Application;

import com.madd.madd.tmdb.PopularMovieList.MovieCatalogModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .movieCatalogModule(new MovieCatalogModule())
                    .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
