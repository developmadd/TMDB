package com.madd.madd.tmdb.Fragments.TVShowCatalog;

import com.madd.madd.tmdb.HTTP.Models.TVShowList;

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

        interface TVShowSelected {
            void onTVShowClick(TVShowList.TVShow tvShow);
        }
    }

    interface Presenter{
        void setView(TVShowCatalogContract.View view);

        void requestTVShowList();
        void filterTVShowList(String query);
        void selectTVShow(TVShowList.TVShow tvShow);

    }

    interface Model{
        void getTVShowPopularList(int page, GetTVShowList tvShowList);
        void getTVShowTopRatedList(int page, GetTVShowList tvShowList);
        void getTVShowOnAirList(int page, GetTVShowList tvShowList);

        interface GetTVShowList{
            void onSuccess(TVShowList tvShowList);
            void onError(String error);
        }

    }



}
