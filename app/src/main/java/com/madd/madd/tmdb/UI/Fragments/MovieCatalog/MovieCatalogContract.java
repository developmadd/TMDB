package com.madd.madd.tmdb.UI.Fragments.MovieCatalog;

import com.madd.madd.tmdb.HTTP.Models.MovieList;

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

    interface Model{
        void getMoviePopularList(int page, GetMovieList movieList);
        void getMovieUpcomingList(int page, GetMovieList movieList);
        void getMovieTopRatedList(int page, GetMovieList movieList);

        interface GetMovieList{
            void onSuccess(MovieList movieList);
            void onError(String error);
        }

    }



}
