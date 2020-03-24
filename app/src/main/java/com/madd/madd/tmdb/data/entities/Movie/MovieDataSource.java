package com.madd.madd.tmdb.data.entities.Movie;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;

import java.util.List;

public interface MovieDataSource {


    interface Repository {
        void getMovie(String movieId, DataSource.GetEntity<Movie> getMovie);

        void refreshMoviePopularList(DataSource.GetList<MovieList.Movie> getMovieList);
        void refreshMovieUpcomingList(DataSource.GetList<MovieList.Movie> getMovieList);
        void refreshMovieTopRatedList(DataSource.GetList<MovieList.Movie> getMovieList);

        void requestNextMoviePopularList(DataSource.GetList<MovieList.Movie> getMovieList);
        void requestNextMovieUpcomingList(DataSource.GetList<MovieList.Movie> getMovieList);
        void requestNextMovieTopRatedList(DataSource.GetList<MovieList.Movie> getMovieList);

        void getFilteredPopularList(String text, DataSource.GetList<MovieList.Movie> getMovieList);
        void getFilteredUpcomingList(String text, DataSource.GetList<MovieList.Movie> getMovieList);
        void getFilteredTopRatedList(String text, DataSource.GetList<MovieList.Movie> getMovieList);
    }

    interface Remote{

        void getMovie(String movieId, DataSource.GetEntity<Movie> getMovie);

        void getMoviePopularList(int page, DataSource.GetEntity<MovieList> movieList);
        void getMovieUpcomingList(int page, DataSource.GetEntity<MovieList> movieList);
        void getMovieTopRatedList(int page, DataSource.GetEntity<MovieList> movieList);
    }

    interface Cache{
        void getMovie(String movieId, DataSource.GetEntity<Movie> getMovie);

        void getMoviePopularList(int page, DataSource.GetEntity<MovieList> movieList);
        void getMovieUpcomingList(int page, DataSource.GetEntity<MovieList> movieList);
        void getMovieTopRatedList(int page, DataSource.GetEntity<MovieList> movieList);

        void getFilteredPopularList(String text, DataSource.GetList<MovieList.Movie> getMovieList);
        void getFilteredUpcomingList(String text, DataSource.GetList<MovieList.Movie> getMovieList);
        void getFilteredTopRatedList(String text, DataSource.GetList<MovieList.Movie> getMovieList);

        void setMovie(Movie movie);
        void setMoviePopularList( List<MovieList.Movie> movieList);
        void setMovieUpcomingList( List<MovieList.Movie> movieList);
        void setMovieTopRatedList( List<MovieList.Movie> movieList);
    }







}
