package com.madd.madd.tmdb.ui.ContentSearch;

import com.madd.madd.tmdb.data.entities.ContentList.ContentListDataSource;

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
