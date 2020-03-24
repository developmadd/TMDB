package com.madd.madd.tmdb.data.entities.Movie;

import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;

import java.util.List;

public interface MovieDataSource {


    interface Repository {
        void getMovie(String movieId, MovieDataSource.GetMovie getMovie);

        void refreshMoviePopularList(GetList<MovieList.Movie> getMovieList);
        void refreshMovieUpcomingList(GetList<MovieList.Movie> getMovieList);
        void refreshMovieTopRatedList(GetList<MovieList.Movie> getMovieList);

        void requestNextMoviePopularList(GetList<MovieList.Movie> getMovieList);
        void requestNextMovieUpcomingList(GetList<MovieList.Movie> getMovieList);
        void requestNextMovieTopRatedList(GetList<MovieList.Movie> getMovieList);

        void getFilteredPopularList(String text, GetList<MovieList.Movie> getMovieList);
        void getFilteredUpcomingList(String text, GetList<MovieList.Movie> getMovieList);
        void getFilteredTopRatedList(String text, GetList<MovieList.Movie> getMovieList);
    }

    interface Remote{

        void getMovie(String movieId, GetMovie getMovie);

        void getMoviePopularList(int page, GetMovieList movieList);
        void getMovieUpcomingList(int page, GetMovieList movieList);
        void getMovieTopRatedList(int page, GetMovieList movieList);
    }

    interface Cache{
        void getMovie(String movieId, GetMovie getMovie);

        void getMoviePopularList(int page, GetMovieList movieList);
        void getMovieUpcomingList(int page, GetMovieList movieList);
        void getMovieTopRatedList(int page, GetMovieList movieList);

        void getFilteredPopularList(String text, GetList<MovieList.Movie> getMovieList);
        void getFilteredUpcomingList(String text, GetList<MovieList.Movie> getMovieList);
        void getFilteredTopRatedList(String text, GetList<MovieList.Movie> getMovieList);

        void setMovie(Movie movie);
        void setMoviePopularList( List<MovieList.Movie> movieList);
        void setMovieUpcomingList( List<MovieList.Movie> movieList);
        void setMovieTopRatedList( List<MovieList.Movie> movieList);
    }



    interface GetMovieList{
        void onSuccess(MovieList movieList);
        void onError(String error);
    }

    interface GetList<T>{
        void onSuccess(List<T> list);
        void onError(String error);
    }

    interface GetMovie{
        void onSuccess(Movie movie);
        void onError(String error);
    }

}
