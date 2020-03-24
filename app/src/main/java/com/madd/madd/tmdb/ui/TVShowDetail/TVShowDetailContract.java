package com.madd.madd.tmdb.ui.TVShowDetail;

import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;

public interface TVShowDetailContract {

    interface View{

        void showTVShow(TVShow tvShow);
        void showCast(Cast cast);

        void showLoadingProgress();
        void hideLoadingProgress();
        void showTVShowError();
        void showCastError();

        String getTVShowId();
    }

    interface Presenter{
        void setView(TVShowDetailContract.View view);

        void getTVShow();
        void getCast();
    }



}
