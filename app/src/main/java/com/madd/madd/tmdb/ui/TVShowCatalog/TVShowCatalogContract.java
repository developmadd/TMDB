package com.madd.madd.tmdb.ui.TVShowCatalog;

import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

import java.util.List;

public interface TVShowCatalogContract {

    interface View{
        void showTVShowList(List<TVShowList.TVShow> tvShowList);
        void clearTVShowList();
        void showEmptyListError();
        void showInternetError();
        void hideError();
        void hideRefresh();
        void openTVShowDetail(TVShowList.TVShow tvShow);

        int getListType();

    }

    interface Presenter{
        void setView(TVShowCatalogContract.View view);

        void requestTVShowList();
        void refreshMovieList();
        void filterTVShowList(String query);
        void selectTVShow(TVShowList.TVShow tvShow);

    }





}
