package com.madd.madd.tmdb.Fragments.MovieDetail;



import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.Movie;
import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.Utilities.References;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailModel implements MovieDetailContract.Model {

    TMDBApi tmdbApi;

    public MovieDetailModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    @Override
    public void getMovie(String movieId, GetMovie getMovie) {
        Call<Movie> movieCall = tmdbApi.getMovie(movieId, References.TMDB_API_KEY,References.TMDB_LANGUAGE);
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
    public void getMovieCast(String movieId, GetCast getCast) {
        Call<Cast> castCall = tmdbApi.getMovieCast(movieId,References.TMDB_API_KEY);
        castCall.enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(Call<Cast> call, Response<Cast> response) {
                getCast.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Cast> call, Throwable t) {
                getCast.onError(t.getLocalizedMessage());
            }
        });
    }
}
