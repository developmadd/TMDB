package com.madd.madd.tmdb.MovieCatalog;

import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.Utilities.References;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCatalogModel implements MovieCatalogContract.Model {

    TMDBApi tmdbApi;

    public MovieCatalogModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    @Override
    public void getMovieList(int page, GetMovieList getMovieList) {


        Call<MovieList> movieList = tmdbApi.getMoviePopularList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        movieList.enqueue(new Callback<MovieList>() {
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
    public void getMovieUpcomingList(int page, GetMovieList getMovieList){

        Call<MovieList> movieList = tmdbApi.getMovieUpcomingList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        movieList.enqueue(new Callback<MovieList>() {
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
    public void getMovieTopRatedList(int page, GetMovieList getMovieList) {

        Call<MovieList> movieList = tmdbApi.getMovieTopRatedList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        movieList.enqueue(new Callback<MovieList>() {
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
