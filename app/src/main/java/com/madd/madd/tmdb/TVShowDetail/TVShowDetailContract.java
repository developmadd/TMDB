package com.madd.madd.tmdb.TVShowDetail;

import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.TVShow;

public interface TVShowDetailContract {

    interface View{

        void showTVShow(TVShow tvShow);
        void showCast(Cast cast);

        void showLoadingProgress();
        void hideLoadingProgress();
        void showTVShowError();
        void showCastError();
        void closeDetail();

        String getTVShowId();
    }

    interface Presenter{
        void setView(TVShowDetailContract.View view);

        void closeDetail();
        void getTVShow();
        void getCast();
    }

    interface Model{

        void getTVShow(String tvShowId, GetTVShow getTVShow);
        void getTVShowCast(String tvShowId, GetCast getCast);

        interface GetTVShow{
            void onSuccess(TVShow tvShow);
            void onError(String error);
        }
        interface GetCast{
            void onSuccess(Cast cast);
            void onError(String error);
        }
    }

}
