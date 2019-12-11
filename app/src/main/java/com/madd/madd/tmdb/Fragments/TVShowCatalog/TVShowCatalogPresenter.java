package com.madd.madd.tmdb.Fragments.TVShowCatalog;

import com.madd.madd.tmdb.HTTP.Models.TVShowList;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class TVShowCatalogPresenter implements TVShowCatalogContract.Presenter {

    private TVShowCatalogContract.View view;
    private TVShowCatalogContract.Model model;

    private List<TVShowList.TVShow> tvShowList = new ArrayList<>();

    public TVShowCatalogPresenter(TVShowCatalogContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(TVShowCatalogContract.View view) {
        this.view = view;
    }

    @Override
    public void requestTVShowList() {
        int page = view.getPage();

        TVShowCatalogContract.Model.GetTVShowList getTVShowList = new TVShowCatalogContract.Model.GetTVShowList() {

            @Override
            public void onSuccess(TVShowList tvShowList) {

                if(!tvShowList.getTvShowList().isEmpty()){
                    view.hideError();
                    view.showTVShowList(tvShowList.getTvShowList(),tvShowList.getPage() + 1);
                } else {
                    view.showEmptyListError();
                }

            }

            @Override
            public void onError(String error) {
                view.showInternetError();
            }
        };

        if( view.getListType() == TVShowCatalogFragment.POPULAR_TYPE ) {
            model.getTVShowPopularList(page, getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.TOP_RATED_TYPE ){
            model.getTVShowTopRatedList(page, getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.ON_AIR_TYPE ){
            model.getTVShowOnAirList(page, getTVShowList);
        }

    }

    @Override
    public void filterTVShowList(String query) {
        if( tvShowList.isEmpty() ) {
            tvShowList = new ArrayList<>(view.getTVShowList());
        }

        List<TVShowList.TVShow> filteredTVShowList = new ArrayList<>();
        view.clearTVShowList();

        if (!query.isEmpty()) {
            String compare = Utilities.cleanString(query);
            for (TVShowList.TVShow tvShow : tvShowList) {

                String original = Utilities.cleanString(tvShow.getName());
                if (original.startsWith(compare)) {
                    filteredTVShowList.add(tvShow);
                }
            }
            for (TVShowList.TVShow tvShow : tvShowList) {

                String original = Utilities.cleanString(tvShow.getName());
                if ( !original.startsWith(compare) && original.contains(compare) ) {
                    filteredTVShowList.add(tvShow);
                }
            }
            if(!filteredTVShowList.isEmpty()){
                view.hideError();
                view.showTVShowList(filteredTVShowList,1);
            } else {
                view.showEmptyListError();
            }

        } else {
            tvShowList.clear();
            requestTVShowList();
        }
    }

    @Override
    public void selectTVShow(TVShowList.TVShow tvShow) {
        if( view != null ){
            view.openTVShowDetail(tvShow);
        }
    }
}
