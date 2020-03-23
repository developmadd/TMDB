package com.madd.madd.tmdb.Data.Cast;

import com.madd.madd.tmdb.Data.Cast.Model.Cast;
import com.madd.madd.tmdb.Data.Movie.Model.Movie;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;

import java.util.List;

public interface CastDataSource {


    interface Repository {
        void getMovieCast(String movieId, GetCast getCast);
        void getTVShowCast(String tvShowId, GetCast getCast);
    }


    interface Remote{

        void getMovieCast(String movieId, GetCast getCast);
        void getTVShowCast(String tvShowId, GetCast getCast);

    }
    interface Cache{

        void getMovieCast(String movieId, GetCast getCast);
        void getTVShowCast(String tvShowId, GetCast getCast);

        void setMovieCast(String movieId, Cast cast);
        void setTVShowCast(String tvShowId, Cast cast);
    }


    interface GetCast{
        void onSuccess(Cast cast);
        void onError(String error);
    }

}
