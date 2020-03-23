package com.madd.madd.tmdb.Data.Cast;

public class CastRepository implements CastDataSource.Repository {

    private CastDataSource.Remote castRemoteDataSource;
    private CastDataSource.Cache castCacheDataSource;

    public CastRepository(CastDataSource.Remote castRemoteDataSource,
                          CastDataSource.Cache castCacheDataSource) {
        this.castRemoteDataSource = castRemoteDataSource;
        this.castCacheDataSource = castCacheDataSource;
    }

    @Override
    public void getMovieCast(String movieId, CastDataSource.GetCast getCast) {
        castRemoteDataSource.getMovieCast(movieId,getCast);
    }

    @Override
    public void getTVShowCast(String tvShowId, CastDataSource.GetCast getCast) {
        castRemoteDataSource.getTVShowCast(tvShowId,getCast);
    }
}
