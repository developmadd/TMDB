package com.madd.madd.tmdb.DI;

import android.app.Application;

import com.madd.madd.tmdb.Data.Cast.CastDataModule;
import com.madd.madd.tmdb.Data.ContentList.ContentListDataModule;
import com.madd.madd.tmdb.Data.Movie.MovieDataModule;
import com.madd.madd.tmdb.Data.TVShow.TVShowDataModule;
import com.madd.madd.tmdb.UI.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.Data.HTTP.HTTPModule;
import com.madd.madd.tmdb.UI.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.UI.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.UI.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.UI.TVShowDetail.TVShowDetailModule;

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
