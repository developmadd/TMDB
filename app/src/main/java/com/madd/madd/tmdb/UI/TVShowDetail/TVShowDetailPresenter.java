package com.madd.madd.tmdb.UI.TVShowDetail;

import com.madd.madd.tmdb.Data.Cast.CastDataSource;
import com.madd.madd.tmdb.Data.Cast.Model.Cast;
import com.madd.madd.tmdb.Data.TVShow.Model.TVShow;
import com.madd.madd.tmdb.Data.TVShow.TVShowDataSource;

public class TVShowDetailPresenter implements TVShowDetailContract.Presenter {

    private TVShowDataSource.Repository tvShowRepository;
    private CastDataSource.Repository castRepository;
    private TVShowDetailContract.View view;

    public TVShowDetailPresenter(TVShowDataSource.Repository tvShowRepository,
                                 CastDataSource.Repository castRepository) {
        this.tvShowRepository = tvShowRepository;
        this.castRepository = castRepository;
    }

    @Override
    public void setView(TVShowDetailContract.View view) {
        this.view = view;
    }



    @Override
    public void getTVShow() {
        if( view != null ){
            view.showLoadingProgress();
            tvShowRepository.getTVShow(view.getTVShowId(), new TVShowDataSource.GetTVShow() {

                @Override
                public void onSuccess(TVShow tvShow) {
                    view.showTVShow(tvShow);
                    view.hideLoadingProgress();
                }

                @Override
                public void onError(String error) {
                    view.showTVShowError();
                    view.hideLoadingProgress();
                }
            });

        }
    }



    @Override
    public void getCast() {
        if( view != null ){

            castRepository.getTVShowCast(view.getTVShowId(), new CastDataSource.GetCast() {
                @Override
                public void onSuccess(Cast cast) {
                    if(!cast.getActorList().isEmpty() ) {
                        view.showCast(cast);
                    } else{
                        view.showCastError();
                    }
                }

                @Override
                public void onError(String error) {
                    view.showCastError();
                }
            });

        }
    }
}
