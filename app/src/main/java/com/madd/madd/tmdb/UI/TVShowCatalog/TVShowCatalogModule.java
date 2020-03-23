package com.madd.madd.tmdb.UI.TVShowCatalog;


import com.madd.madd.tmdb.Data.HTTP.TMDBApi;
import com.madd.madd.tmdb.Data.TVShow.TVShowDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class TVShowCatalogModule {

    @Provides
    public TVShowCatalogContract.Presenter provideTVShowCatalogPresenter(
            TVShowDataSource.Repository repository){
        return new TVShowCatalogPresenter(repository);
    }


}
