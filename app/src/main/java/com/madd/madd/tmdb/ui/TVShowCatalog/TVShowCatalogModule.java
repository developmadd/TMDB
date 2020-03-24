package com.madd.madd.tmdb.ui.TVShowCatalog;


import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;

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
