package com.madd.madd.tmdb.UI.TVShowDetail;

import com.madd.madd.tmdb.Data.Cast.CastDataSource;
import com.madd.madd.tmdb.Data.HTTP.TMDBApi;
import com.madd.madd.tmdb.Data.TVShow.TVShowDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class TVShowDetailModule {

    @Provides
    public TVShowDetailContract.Presenter provideTVShowPresenter(
            TVShowDataSource.Repository tvShowRepository,
            CastDataSource.Repository castRepository){
        return new TVShowDetailPresenter(tvShowRepository,castRepository);
    }


}
