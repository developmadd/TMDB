package com.madd.madd.tmdb.ui.TVShowCatalog;

import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;
import com.madd.madd.tmdb.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class TVShowCatalogPresenter implements TVShowCatalogContract.Presenter {

    private TVShowCatalogContract.View view;
    private TVShowDataSource.Repository repository;

    private List<TVShowList.TVShow> tvShowList = new ArrayList<>();

    public TVShowCatalogPresenter(TVShowDataSource.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(TVShowCatalogContract.View view) {
        this.view = view;
    }

    @Override
    public void requestTVShowList() {
        int page = view.getPage();

        TVShowDataSource.GetTVShowList getTVShowList = new TVShowDataSource.GetTVShowList() {

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
            repository.getTVShowPopularList(page, getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.TOP_RATED_TYPE ){
            repository.getTVShowTopRatedList(page, getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.ON_AIR_TYPE ){
            repository.getTVShowOnAirList(page, getTVShowList);
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
