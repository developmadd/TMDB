package com.madd.madd.tmdb.di;

import android.app.Application;

import com.madd.madd.tmdb.data.entities.Cast.CastDataModule;
import com.madd.madd.tmdb.data.entities.ContentList.ContentListDataModule;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataModule;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataModule;
import com.madd.madd.tmdb.ui.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.data.HTTP.HTTPModule;
import com.madd.madd.tmdb.ui.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.ui.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.ui.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.ui.TVShowDetail.TVShowDetailModule;

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

                    .hTTPModule(new HTTPModule())
                    .movieDataModule(new MovieDataModule())
                    .tVShowDataModule(new TVShowDataModule())
                    .castDataModule(new CastDataModule())
                    .contentListDataModule(new ContentListDataModule())
                    .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
