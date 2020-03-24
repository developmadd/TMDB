package com.madd.madd.tmdb.data.entities.ContentList;

import com.madd.madd.tmdb.data.entities.ContentList.Source.Remote.ContentListRemoteDataSource;
import com.madd.madd.tmdb.data.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class ContentListDataModule {
    @Provides
    public ContentListDataSource.Repository provideContentListRepository(
            ContentListDataSource.Remote contentListRemoteDataSource){
        return new ContentListRepository(contentListRemoteDataSource);
    }

    @Provides
    public ContentListDataSource.Remote provideContentListDataSource(
            TMDBApi api){
        return new ContentListRemoteDataSource(api);
    }

}
