package com.madd.madd.tmdb.MovieDetail;

import com.madd.madd.tmdb.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    public MovieDetailContract.Presenter provideMovieDetailPresenter(MovieDetailContract.Model model){
        return new MovieDetailPresenter(model);
    }

    @Provides
    public MovieDetailContract.Model provideMovieDetailModel(TMDBApi tmdbApi){
        return new MovieDetailModel(tmdbApi);
    }

}
