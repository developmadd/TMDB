package com.madd.madd.tmdb.UI.Fragments.TVShowDetail;

import com.madd.madd.tmdb.HTTP.TMDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class TVShowDetailModule {

    @Provides
    public TVShowDetailContract.Presenter provideTVShowPresenter(TVShowDetailContract.Model model){
        return new TVShowDetailPresenter(model);
    }

    @Provides
    public TVShowDetailContract.Model provideTVShowModel(TMDBApi tmdbApi){
        return new TVShowDetailModel(tmdbApi);
    }

}
