package com.madd.madd.tmdb.ui.TVShowCatalog;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;

import java.util.List;

public class TVShowCatalogPresenter implements TVShowCatalogContract.Presenter {

    private TVShowCatalogContract.View view;
    private TVShowDataSource.Repository repository;

    TVShowCatalogPresenter(TVShowDataSource.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(TVShowCatalogContract.View view) {
        this.view = view;
    }

    @Override
    public void requestTVShowList() {

        DataSource.GetList<TVShowList.TVShow> getTVShowList = new DataSource.GetList<TVShowList.TVShow>() {

            @Override
            public void onSuccess(List<TVShowList.TVShow> tvShowList) {
                if(!tvShowList.isEmpty()){
                    view.hideError();
                    view.showTVShowList(tvShowList);
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
            repository.requestNextTVShowPopularList(getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.TOP_RATED_TYPE ){
            repository.requestNextTVShowTopRatedList(getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.ON_AIR_TYPE ){
            repository.requestNextTVShowOnAirList(getTVShowList);
        }

    }

    @Override
    public void refreshMovieList() {

        DataSource.GetList<TVShowList.TVShow> tvShowGetList = new DataSource.GetList<TVShowList.TVShow>() {
            @Override
            public void onSuccess(List<TVShowList.TVShow> tvShowList) {
                if(!tvShowList.isEmpty()){
                    view.hideError();
                    view.showTVShowList(tvShowList);
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
            repository.refreshTVShowPopularList(tvShowGetList);
        } else if( view.getListType() == TVShowCatalogFragment.TOP_RATED_TYPE ){
            repository.refreshTVShowTopRatedList(tvShowGetList);
        } else if( view.getListType() == TVShowCatalogFragment.ON_AIR_TYPE ){
            repository.refreshTVShowOnAirList(tvShowGetList);
        }
    }

    @Override
    public void filterTVShowList(String query) {
        view.clearTVShowList();

        DataSource.GetList<TVShowList.TVShow> getTVShowList = new DataSource.GetList<TVShowList.TVShow>() {

            @Override
            public void onSuccess(List<TVShowList.TVShow> tvShowList) {
                view.hideError();
                view.showTVShowList(tvShowList);
            }

            @Override
            public void onError(String error) {
                view.showEmptyListError();
            }
        };

        if( view.getListType() == TVShowCatalogFragment.POPULAR_TYPE ) {
            repository.getFilteredPopularList(query, getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.TOP_RATED_TYPE ){
            repository.getFilteredTopRatedList(query, getTVShowList);
        } else if( view.getListType() == TVShowCatalogFragment.ON_AIR_TYPE ){
            repository.getFilteredOnAirList(query, getTVShowList);
        }
    }

    @Override
    public void selectTVShow(TVShowList.TVShow tvShow) {
        if( view != null ){
            view.openTVShowDetail(tvShow);
        }
    }
}
