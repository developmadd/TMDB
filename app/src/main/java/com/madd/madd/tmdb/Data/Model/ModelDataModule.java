package com.madd.madd.tmdb.Data.Model;

import com.madd.madd.tmdb.Data.Model.Source.Cache.ModelCacheDataSource;
import com.madd.madd.tmdb.Data.Model.Source.Remote.ModelRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelDataModule {

    @Provides
    public ModelDataSource.Repository provideModelRepository(
            ModelDataSource.Remote movieRemoteDataSource,
            ModelDataSource.Cache movieCacheDataSource){
        return new ModelRepository(movieRemoteDataSource,movieCacheDataSource);
    }

    @Provides
    public ModelDataSource.Remote provideMovieRemoteDataSource(){
        return new ModelRemoteDataSource(/*api*/);
    }

    @Singleton
    @Provides
    public ModelDataSource.Cache provideMovieCacheDataSource(){
        return new ModelCacheDataSource();
    }
}
