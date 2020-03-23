package com.madd.madd.tmdb.Data.Movie.Source.Remote;

import com.madd.madd.tmdb.Data.HTTP.TMDBApi;
import com.madd.madd.tmdb.Data.Movie.Model.Movie;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.HTTP.HTTPModule;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;
import com.madd.madd.tmdb.UI.MovieDetail.MovieDetailContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource.Remote {

    private TMDBApi api;

    public MovieRemoteDataSource(TMDBApi api) {
        this.api = api;
    }

    @Override
    public void getMovie(String movieId, MovieDataSource.GetMovie getMovie) {
        Call<Movie> movieCall = api.getMovie(movieId,
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                getMovie.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                getMovie.onError(t.getLocalizedMessage());
            }
        });
    }


    @Override
    public void getMoviePopularList(int page, MovieDataSource.GetMovieList getMovieList) {
        Call<MovieList> movieListCall = api.getMoviePopularList(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
                page);

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                getMovieList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                getMovieList.onError(t.getLocalizedMessage());
            }


        });
    }

    @Override
    public void getMovieUpcomingList(int page, MovieDataSource.GetMovieList getMovieList){

        Call<MovieList> movieListCall = api.getMovieUpcomingList(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
                page);

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                getMovieList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                getMovieList.onError(t.getLocalizedMessage());
            }


        });

    }

    @Override
    public void getMovieTopRatedList(int page, MovieDataSource.GetMovieList getMovieList) {

        Call<MovieList> movieListCall = api.getMovieTopRatedList(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
                page);

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                getMovieList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                getMovieList.onError(t.getLocalizedMessage());
            }


        });

    }

}
