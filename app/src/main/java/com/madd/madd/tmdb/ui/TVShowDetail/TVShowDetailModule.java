package com.madd.madd.tmdb.ui.TVShowDetail;

import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;

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
