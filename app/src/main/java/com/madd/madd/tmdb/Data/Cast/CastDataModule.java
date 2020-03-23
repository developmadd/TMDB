package com.madd.madd.tmdb.Data.Cast;

import com.madd.madd.tmdb.Data.Cast.Source.Local.CastCacheDataSource;
import com.madd.madd.tmdb.Data.Cast.Source.Remote.CastRemoteDataSource;
import com.madd.madd.tmdb.Data.HTTP.TMDBApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CastDataModule {

    @Provides
    public CastDataSource.Repository provideMovieCatalogRepository(
            CastDataSource.Remote castRemoteDataSource,
            CastDataSource.Cache castCacheDataSource){
        return new CastRepository(castRemoteDataSource,castCacheDataSource);
    }

    @Provides
    public CastDataSource.Remote provideCastRemoteDataSource(
            TMDBApi api){
        return new CastRemoteDataSource(api);
    }

    @Singleton
    @Provides
    public CastDataSource.Cache provideCastCacheDataSource(){
        return new CastCacheDataSource();
    }
}
