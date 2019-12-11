package com.madd.madd.tmdb.Fragments.MovieCatalog;

import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.Utilities.References;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCatalogModel implements MovieCatalogContract.Model {

    private TMDBApi tmdbApi;

    MovieCatalogModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }



    @Override
    public void getMoviePopularList(int page, GetMovieList getMovieList) {
        Call<MovieList> movieListCall = tmdbApi.getMoviePopularList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

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
    public void getMovieUpcomingList(int page, GetMovieList getMovieList){

        Call<MovieList> movieListCall = tmdbApi.getMovieUpcomingList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

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
    public void getMovieTopRatedList(int page, GetMovieList getMovieList) {

        Call<MovieList> movieListCall = tmdbApi.getMovieTopRatedList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

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