package com.madd.madd.tmdb.data.entities.TVShow;

import com.madd.madd.tmdb.data.http.TMDBApi;
import com.madd.madd.tmdb.data.entities.TVShow.Source.Local.TVShowCacheDataSource;
import com.madd.madd.tmdb.data.entities.TVShow.Source.Remote.TVShowRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TVShowDataModule {

    @Provides
    public TVShowDataSource.Repository provideTVShowRepository(
            TVShowDataSource.Remote tvShowRemoteDataSource,
            TVShowDataSource.Cache tvShowCacheDataSource){
        return new TVShowRepository(tvShowRemoteDataSource,tvShowCacheDataSource);
    }

    @Provides
    public TVShowDataSource.Remote provideTVShowRemoteDataSource(
            TMDBApi api){
        return new TVShowRemoteDataSource(api);
    }

    @Singleton
    @Provides
    public TVShowDataSource.Cache provideTVShowCacheDataSource(){
        return new TVShowCacheDataSource();
    }
}
