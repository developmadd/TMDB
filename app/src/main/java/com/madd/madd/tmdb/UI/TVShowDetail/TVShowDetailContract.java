package com.madd.madd.tmdb.UI.TVShowDetail;

import com.madd.madd.tmdb.Data.Cast.Model.Cast;
import com.madd.madd.tmdb.Data.TVShow.Model.TVShow;

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
