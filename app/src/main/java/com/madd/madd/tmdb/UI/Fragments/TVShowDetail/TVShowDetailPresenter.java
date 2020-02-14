package com.madd.madd.tmdb.UI.Fragments.TVShowDetail;

import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.TVShow;

public class TVShowDetailPresenter implements TVShowDetailContract.Presenter {

    private TVShowDetailContract.Model model;
    private TVShowDetailContract.View view;

    public TVShowDetailPresenter(TVShowDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(TVShowDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void closeDetail() {
        if( view != null ){
            view.closeDetail();
        }
    }

    @Override
    public void getTVShow() {
        if( view != null ){
            view.showLoadingProgress();
            model.getTVShow(view.getTVShowId(), new TVShowDetailContract.Model.GetTVShow() {

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

            model.getTVShowCast(view.getTVShowId(), new TVShowDetailContract.Model.GetCast() {
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
