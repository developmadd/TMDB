package com.madd.madd.tmdb.data.entities.Cast;

import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;

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
