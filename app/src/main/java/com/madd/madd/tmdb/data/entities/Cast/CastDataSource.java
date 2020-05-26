package com.madd.madd.tmdb.data.entities.Cast;

import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.DataSource;

public interface CastDataSource {


    interface Repository {
        void getMovieCast(String movieId, DataSource.GetEntity<Cast> getCast);
        void getTVShowCast(String tvShowId, DataSource.GetEntity<Cast> getCast);
    }


    interface Remote{

        void getMovieCast(String movieId, DataSource.GetEntity<Cast> getCast);
        void getTVShowCast(String tvShowId, DataSource.GetEntity<Cast> getCast);

    }
    interface Cache{

        void getMovieCast(String movieId, DataSource.GetEntity<Cast> getCast);
        void getTVShowCast(String tvShowId, DataSource.GetEntity<Cast> getCast);

        void setMovieCast(String movieId, Cast cast);
        void setTVShowCast(String tvShowId, Cast cast);
    }




}
