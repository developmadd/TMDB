package com.madd.madd.tmdb.Fragments.TVShowDetail;



import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.TVShow;
import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.HTTP.TMDBModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowDetailModel implements TVShowDetailContract.Model {

    private TMDBApi tmdbApi;

    TVShowDetailModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    @Override
    public void getTVShow(String tvShowId, GetTVShow getTVShow) {
        Call<TVShow> tvShowCall = tmdbApi.getTVShow(tvShowId,
                TMDBModule.TMDB_API_KEY,
                TMDBModule.TMDB_LANGUAGE);
        tvShowCall.enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(Call<TVShow> call, Response<TVShow> response) {
                getTVShow.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShow> call, Throwable t) {
                getTVShow.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getTVShowCast(String tvShowId, GetCast getCast) {

        Call<Cast> castCall = tmdbApi.getTVShowCast(tvShowId,
                TMDBModule.TMDB_API_KEY);

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
