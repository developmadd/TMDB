package com.madd.madd.tmdb.Data.Movie;

import com.madd.madd.tmdb.Data.Movie.Model.Movie;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;

import java.util.List;

public interface MovieDataSource {


    interface Repository {
        void getMovie(String movieId, MovieDataSource.GetMovie getMovie);

        void getMoviePopularList(int page, MovieDataSource.GetMovieList getMovieList);
        void getMovieUpcomingList(int page, MovieDataSource.GetMovieList getMovieList);
        void getMovieTopRatedList(int page, MovieDataSource.GetMovieList getMovieList);
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

        void setMoviePopularList( List<MovieList.Movie> movieList);
        void setMovieUpcomingList( List<MovieList.Movie> movieList);
        void setMovieTopRatedList( List<MovieList.Movie> movieList);
    }



    interface GetMovieList{
        void onSuccess(MovieList movieList);
        void onError(String error);
    }

    interface GetMovie{
        void onSuccess(Movie movie);
        void onError(String error);
    }

}
