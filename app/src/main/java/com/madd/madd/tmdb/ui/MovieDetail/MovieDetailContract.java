package com.madd.madd.tmdb.ui.MovieDetail;

import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;

public interface MovieDetailContract {

    interface View{

        void showMovie(Movie movie);
        void showCast(Cast cast);

        void showLoadingProgress();
        void hideLoadingProgress();
        void showMovieError();
        void showCastError();

        String getMovieId();
    }

    interface Presenter{
        void setView(MovieDetailContract.View view);

        void getMovie();
        void getCast();
    }



}
