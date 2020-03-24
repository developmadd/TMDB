package com.madd.madd.tmdb.ui.MovieCatalog;

import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataSource;

import java.util.List;

public class MovieCatalogPresenter implements MovieCatalogContract.Presenter {

    private MovieDataSource.Repository repository;
    private MovieCatalogContract.View view;

    MovieCatalogPresenter(MovieDataSource.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(MovieCatalogContract.View view) {
        this.view = view;
    }



    @Override
    public void refreshMovieList() {
        if(view != null) {
            view.clearMovieList();
            MovieDataSource.GetList<MovieList.Movie> getMovieList = new MovieDataSource.GetList<MovieList.Movie>() {
                @Override
                public void onSuccess(List<MovieList.Movie> movieList) {
                    if(!movieList.isEmpty()){
                        view.hideError();
                        view.showMovieList(movieList);
                    } else {
                        view.showEmptyListError();
                    }
                    view.hideRefresh();
                }

                @Override
                public void onError(String error) {
                    view.showInternetError();
                    view.hideRefresh();
                }
            };

            if( view.getListType() == MovieCatalogFragment.POPULAR_TYPE ) {
                repository.refreshMoviePopularList(getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.TOP_RATED_TYPE ){
                repository.refreshMovieTopRatedList(getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.UPCOMING_TYPE ){
                repository.refreshMovieUpcomingList(getMovieList);
            }

        }
    }

    @Override
    public void requestMovieList() {
        if( view != null ){

            MovieDataSource.GetList<MovieList.Movie> getMovieList = new MovieDataSource.GetList<MovieList.Movie>() {
                @Override
                public void onSuccess(List<MovieList.Movie> movieList) {
                    if(!movieList.isEmpty()){
                        view.hideError();
                        view.showMovieList(movieList);
                    } else {
                        view.showEmptyListError();
                    }
                }

                @Override
                public void onError(String error) {
                    view.showInternetError();
                }
            };

            if( view.getListType() == MovieCatalogFragment.POPULAR_TYPE ) {
                repository.requestNextMoviePopularList(getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.TOP_RATED_TYPE ){
                repository.requestNextMovieTopRatedList(getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.UPCOMING_TYPE ){
                repository.requestNextMovieUpcomingList(getMovieList);
            }

        }
    }

    @Override
    public void filterMovieList(String query) {
        view.clearMovieList();

        MovieDataSource.GetList<MovieList.Movie> getMovieList = new MovieDataSource.GetList<MovieList.Movie>() {
            @Override
            public void onSuccess(List<MovieList.Movie> movieList) {
                view.hideError();
                view.showMovieList(movieList);
            }

            @Override
            public void onError(String error) {
                view.showEmptyListError();
            }
        };

        if( view.getListType() == MovieCatalogFragment.POPULAR_TYPE ) {
            repository.getFilteredPopularList(query, getMovieList);
        } else if( view.getListType() == MovieCatalogFragment.TOP_RATED_TYPE ){
            repository.getFilteredTopRatedList(query, getMovieList);
        } else if( view.getListType() == MovieCatalogFragment.UPCOMING_TYPE ){
            repository.getFilteredUpcomingList(query, getMovieList);
        }

    }

    @Override
    public void selectMovie( MovieList.Movie movie ) {
        if( view != null ){
            view.openMovieDetail(movie);
        }
    }
}
