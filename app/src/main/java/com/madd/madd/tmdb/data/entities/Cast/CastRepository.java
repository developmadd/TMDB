package com.madd.madd.tmdb.data.entities.Cast;

import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.DataSource;

public class CastRepository implements CastDataSource.Repository {

    private CastDataSource.Remote castRemoteDataSource;
    private CastDataSource.Cache castCacheDataSource;

    public CastRepository(CastDataSource.Remote castRemoteDataSource,
                          CastDataSource.Cache castCacheDataSource) {
        this.castRemoteDataSource = castRemoteDataSource;
        this.castCacheDataSource = castCacheDataSource;
    }

    @Override
    public void getMovieCast(String movieId, DataSource.GetEntity<Cast> getCast) {
        castCacheDataSource.getMovieCast(movieId, new DataSource.GetEntity<Cast>() {
            @Override
            public void onSuccess(Cast cast) {
                getCast.onSuccess(cast);
            }

            @Override
            public void onError(String error) {
                castRemoteDataSource.getMovieCast(movieId, new DataSource.GetEntity<Cast>() {
                    @Override
                    public void onSuccess(Cast cast) {
                        getCast.onSuccess(cast);
                        castCacheDataSource.setMovieCast(movieId,cast);
                    }

                    @Override
                    public void onError(String error) {
                        getCast.onError(error);
                    }
                });
            }
        });

    }

    @Override
    public void getTVShowCast(String tvShowId, DataSource.GetEntity<Cast> getCast) {
        castCacheDataSource.getTVShowCast(tvShowId, new DataSource.GetEntity<Cast>() {
            @Override
            public void onSuccess(Cast cast) {
                getCast.onSuccess(cast);
            }

            @Override
            public void onError(String error) {
                castRemoteDataSource.getTVShowCast(tvShowId, new DataSource.GetEntity<Cast>() {
                    @Override
                    public void onSuccess(Cast cast) {
                        getCast.onSuccess(cast);
                        castCacheDataSource.setTVShowCast(tvShowId,cast);
                    }

                    @Override
                    public void onError(String error) {
                        getCast.onError(error);
                    }
                });
            }
        });
    }
}
