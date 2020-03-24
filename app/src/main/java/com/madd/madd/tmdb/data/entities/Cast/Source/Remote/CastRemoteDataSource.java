package com.madd.madd.tmdb.data.entities.Cast.Source.Remote;

import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.Cast.Model.Cast;
import com.madd.madd.tmdb.data.http.TMDBApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRemoteDataSource implements CastDataSource.Remote {

    private TMDBApi api;

    public CastRemoteDataSource(TMDBApi api) {
        this.api = api;
    }

    @Override
    public void getMovieCast(String movieId, CastDataSource.GetCast getCast) {
        Call<Cast> castCall = api.getMovieCast(movieId,
                TMDBApi.TMDB_API_KEY);
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

    @Override
    public void getTVShowCast(String tvShowId, CastDataSource.GetCast getCast) {
        Call<Cast> castCall = api.getTVShowCast(tvShowId,
                TMDBApi.TMDB_API_KEY);

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
