package com.madd.madd.tmdb.ui.MovieCatalog;

import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;

import java.util.List;

public interface MovieCatalogContract {

    interface View{
        void showMovieList(List<MovieList.Movie> movieList);
        void clearMovieList();
        void showEmptyListError();
        void showInternetError();
        void hideError();
        void hideRefresh();
        void openMovieDetail(MovieList.Movie movie);

        int getListType();

    }

    interface Presenter{
        void setView(MovieCatalogContract.View view);

        void refreshMovieList();
        void requestMovieList();
        void filterMovieList(String query);
        void selectMovie(MovieList.Movie movie);

    }










}
