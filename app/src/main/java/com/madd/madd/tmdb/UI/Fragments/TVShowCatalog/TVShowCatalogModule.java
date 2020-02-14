package com.madd.madd.tmdb.UI.Fragments.TVShowCatalog;


import com.madd.madd.tmdb.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class TVShowCatalogModule {

    @Provides
    public TVShowCatalogContract.Presenter provideTVShowCatalogPresenter(TVShowCatalogContract.Model model){
        return new TVShowCatalogPresenter(model);
    }

    @Provides
    public TVShowCatalogContract.Model provideTVShowCatalogModel(TMDBApi tmdbApi){
        return new TVShowCatalogModel(tmdbApi);
    }

}
