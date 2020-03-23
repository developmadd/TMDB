package com.madd.madd.tmdb.UI.MovieCatalog;

import com.madd.madd.tmdb.Data.Movie.MovieDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieCatalogModule {

    @Provides
    public MovieCatalogContract.Presenter provideMovieCatalogPresenter(
            MovieDataSource.Repository repository){
        return new MovieCatalogPresenter(repository);
    }

}
