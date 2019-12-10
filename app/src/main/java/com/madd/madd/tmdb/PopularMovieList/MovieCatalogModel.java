package com.madd.madd.tmdb.PopularMovieList;

import com.madd.madd.tmdb.Models.MovieList;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Retrofit.API;
import com.madd.madd.tmdb.Utilities.Retrofit.TMDBService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCatalogModel implements MovieCatalogContract.Model {

    @Override
    public void getMovieList(int page, GetMovieList getMovieList) {

        TMDBService service = API.getAPI().create(TMDBService.class);
        Call<MovieList> movieList = service.getMoviePopularList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

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

        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<MovieList> movieList = service.getMovieUpcomingList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

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


        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<MovieList> movieList = service.getMovieTopRatedList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

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
