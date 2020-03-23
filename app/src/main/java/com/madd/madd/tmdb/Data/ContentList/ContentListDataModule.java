package com.madd.madd.tmdb.Data.ContentList;

import com.madd.madd.tmdb.Data.ContentList.Source.Remote.ContentListRemoteDataSource;
import com.madd.madd.tmdb.Data.HTTP.TMDBApi;

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
