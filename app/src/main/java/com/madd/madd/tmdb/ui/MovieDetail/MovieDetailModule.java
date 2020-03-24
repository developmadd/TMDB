package com.madd.madd.tmdb.ui.MovieDetail;

import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataSource;

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
