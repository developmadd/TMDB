package com.madd.madd.tmdb.data.entities.Cast.Source.Local;

import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;

import java.util.HashMap;
import java.util.Map;

public class CastCacheDataSource implements CastDataSource.Cache {

    private Map<String,Cast> movieCastMap = new HashMap<>();
    private Map<String,Cast> tvShowCastMap = new HashMap<>();

    private long CACHE_LIFETIME = 1000 * 60;

    @Override
    public void getMovieCast(String movieId, CastDataSource.GetCast getCast) {
        Cast cast = movieCastMap.get(movieId);
        if( cast != null ){
            if( System.currentTimeMillis() - cast.getTimeStap() < CACHE_LIFETIME  ){
                getCast.onSuccess(cast);
                return;
            } else {
                movieCastMap.remove(movieId);
            }
        }
        getCast.onError("EMPTY");
    }

    @Override
    public void getTVShowCast(String tvShowId, CastDataSource.GetCast getCast) {
        Cast cast = tvShowCastMap.get(tvShowId);
        if( cast != null ){
            if( System.currentTimeMillis() - cast.getTimeStap() < CACHE_LIFETIME  ){
                getCast.onSuccess(cast);
                return;
            } else {
                tvShowCastMap.remove(tvShowId);
            }
        }
        getCast.onError("EMPTY");
    }

    @Override
    public void setMovieCast(String movieId, Cast cast) {
        cast.setTimeStap();
        movieCastMap.put(movieId,cast);
    }

    @Override
    public void setTVShowCast(String tvShowId, Cast cast) {
        cast.setTimeStap();
        tvShowCastMap.put(tvShowId,cast);
    }
}
