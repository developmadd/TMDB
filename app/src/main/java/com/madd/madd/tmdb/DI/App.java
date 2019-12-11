package com.madd.madd.tmdb.DI;

import android.app.Application;

import com.madd.madd.tmdb.Fragments.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.HTTP.TMDBModule;
import com.madd.madd.tmdb.Fragments.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.Fragments.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.Fragments.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.Fragments.TVShowDetail.TVShowDetailModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .movieCatalogModule(new MovieCatalogModule())
                    .tVShowCatalogModule(new TVShowCatalogModule())
                    .movieDetailModule(new MovieDetailModule())
                    .tVShowDetailModule(new TVShowDetailModule())
                    .contentSearchModule(new ContentSearchModule())
                    .tMDBModule(new TMDBModule())
                    .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
