package com.madd.madd.tmdb.UI.Fragments.MovieCatalog;

import com.madd.madd.tmdb.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieCatalogModule {

    @Provides
    public MovieCatalogContract.Presenter provideMovieCatalogPresenter(MovieCatalogContract.Model model){
        return new MovieCatalogPresenter(model);
    }

    @Provides
    public MovieCatalogContract.Model provideMovieCatalogModel(TMDBApi tmdbApi){
        return new MovieCatalogModel(tmdbApi);
    }
}
