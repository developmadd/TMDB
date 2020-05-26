package com.madd.madd.tmdb.ui.MovieDetail;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataSource;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDataSource.Repository movieRepository;
    private CastDataSource.Repository castRepository;
    private MovieDetailContract.View view;

    public MovieDetailPresenter(MovieDataSource.Repository movieRepository,
                                CastDataSource.Repository castRepository) {
        this.movieRepository = movieRepository;
        this.castRepository = castRepository;
    }

    @Override
    public void setView(MovieDetailContract.View view) {
        this.view = view;
    }


    @Override
    public void getMovie() {
        if( view != null ) {
            view.showLoadingProgress();
            movieRepository.getMovie(view.getMovieId(), new DataSource.GetEntity<Movie>() {
                @Override
                public void onSuccess(Movie movie) {
                    view.showMovie(movie);
                    view.hideLoadingProgress();
                }

                @Override
                public void onError(String error) {
                    view.showMovieError();
                    view.hideLoadingProgress();
                }
            });

        }
    }

    @Override
    public void getCast() {
        if( view != null ){

            castRepository.getMovieCast(view.getMovieId(), new DataSource.GetEntity<Cast>() {
                @Override
                public void onSuccess(Cast cast) {
                    if(!cast.getActorList().isEmpty() ) {
                        view.showCast(cast);
                    } else{
                        view.showCastError();
                    }
                }

                @Override
                public void onError(String error) {
                    view.showCastError();
                }
            });

        }
    }
}
