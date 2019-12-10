package com.madd.madd.tmdb.MovieCatalog;

import com.madd.madd.tmdb.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieCatalogModule {

    @Provides
    public MovieCatalogContract.Presenter providePopularMovieListPresenter(MovieCatalogContract.Model model){
        return new MovieCatalogPresenter(model);
    }

    @Provides
    public MovieCatalogContract.Model providePopularMovieListModel(TMDBApi tmdbApi){
        return new MovieCatalogModel(tmdbApi);
    }
}
