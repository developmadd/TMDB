package com.madd.madd.tmdb.UI.ContentSearch;

import com.madd.madd.tmdb.Data.ContentList.ContentListDataSource;
import com.madd.madd.tmdb.Data.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class ContentSearchModule {

    @Provides
    public ContentSearchContract.Presenter provideContentSearchPresenter(
            ContentListDataSource.Repository repository){
        return new ContentSearchPresenter(repository);
    }


}
