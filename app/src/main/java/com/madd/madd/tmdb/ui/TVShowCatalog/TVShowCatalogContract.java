package com.madd.madd.tmdb.ui.TVShowCatalog;

import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

import java.util.List;

public interface TVShowCatalogContract {

    interface View{
        void showTVShowList(List<TVShowList.TVShow> tvShowList, int page);
        void clearTVShowList();
        void showEmptyListError();
        void showInternetError();
        void hideError();
        void openTVShowDetail(TVShowList.TVShow tvShow);

        List<TVShowList.TVShow> getTVShowList();
        int getPage();
        int getListType();

    }

    interface Presenter{
        void setView(TVShowCatalogContract.View view);

        void requestTVShowList();
        void filterTVShowList(String query);
        void selectTVShow(TVShowList.TVShow tvShow);

    }





}
