package com.madd.madd.tmdb.UI.MovieDetail;

import com.madd.madd.tmdb.Data.Cast.CastDataSource;
import com.madd.madd.tmdb.Data.HTTP.TMDBApi;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;
import com.madd.madd.tmdb.UI.MovieCatalog.MovieCatalogContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    public MovieDetailContract.Presenter provideMovieDetailPresenter(
            MovieDataSource.Repository movieRepository,
            CastDataSource.Repository castRepository){
        return new MovieDetailPresenter(movieRepository,castRepository);
    }


}
