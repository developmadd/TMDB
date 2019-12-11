package com.madd.madd.tmdb.Fragments.ContentSearch;

import com.madd.madd.tmdb.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class ContentSearchModule {

    @Provides
    public ContentSearchContract.Presenter provideContentSearchPresenter(ContentSearchContract.Model model){
        return new ContentSearchPresenter(model);
    }

    @Provides
    public ContentSearchContract.Model provideContentSearchModel(TMDBApi tmdbApi){
        return new ContentSearchModel(tmdbApi);
    }

}
