package com.madd.madd.tmdb.UI.MovieCatalog;

import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;

import java.util.List;

public interface MovieCatalogContract {

    interface View{
        void showMovieList(List<MovieList.Movie> movieList, int page);
        void clearMovieList();
        void showEmptyListError();
        void showInternetError();
        void hideError();
        void openMovieDetail(MovieList.Movie movie);

        List<MovieList.Movie> getMovieList();
        int getPage();
        int getListType();

    }

    interface Presenter{
        void setView(MovieCatalogContract.View view);

        void requestMovieList();
        void filterMovieList(String query);
        void selectMovie(MovieList.Movie movie);

    }










}
