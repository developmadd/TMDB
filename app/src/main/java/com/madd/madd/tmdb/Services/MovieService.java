package com.madd.madd.tmdb.Services;

import com.madd.madd.tmdb.Models.Cast;
import com.madd.madd.tmdb.Models.MovieList;
import com.madd.madd.tmdb.Models.Movie;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Retrofit.API;
import com.madd.madd.tmdb.Utilities.Retrofit.TMDBService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieService {



    public interface GetMovie{
        void onSuccess(Movie movie);
        void onError(String error);
    }
    public interface GetCast{
        void onSuccess(Cast cast);
        void onError(String error);
    }


    // Read

    public static void getMovie(String movieId,
                                GetMovie getMovie) {


        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<Movie> movieCall = service.getMovie(movieId,References.TMDB_API_KEY, References.TMDB_LANGUAGE);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                getMovie.onSuccess(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                getMovie.onError(t.getLocalizedMessage());
            }
        });

    }




    // Lists

    public static void getMovieCast(String movieId,
                                    GetCast getCast ){

        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<Cast> movieCall = service.getMovieCast(movieId,References.TMDB_API_KEY);
        movieCall.enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(Call<Cast> call, Response<Cast> response) {
                Cast cast = response.body();
                getCast.onSuccess(cast);
            }

            @Override
            public void onFailure(Call<Cast> call, Throwable t) {
                getCast.onError(t.getLocalizedMessage());
            }
        });

    }






    /*public static void getMovieListByQuery(String query, int page,
                                           GetContentList getMovieList){

        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<MovieList> movieList = service.getMovieListByQuery(References.TMDB_API_KEY,References.TMDB_LANGUAGE,query,page);

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

    }*/



}

