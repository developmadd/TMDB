package com.madd.madd.tmdb.MovieDetail;

import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.Movie;

public interface MovieDetailContract {

    interface View{

        void showMovie(Movie movie);
        void showCast(Cast cast);

        void showLoadingProgress();
        void hideLoadingProgress();
        void showMovieError();
        void showCastError();
        void closeDetail();

        String getMovieId();
    }

    interface Presenter{
        void setView(MovieDetailContract.View view);

        void closeDetail();
        void getMovie();
        void getCast();
    }

    interface Model{

        void getMovie(String movieId,GetMovie getMovie);
        void getMovieCast(String movieId, GetCast getCast);

        interface GetMovie{
            void onSuccess(Movie movie);
            void onError(String error);
        }
        interface GetCast{
            void onSuccess(Cast cast);
            void onError(String error);
        }
    }

}
