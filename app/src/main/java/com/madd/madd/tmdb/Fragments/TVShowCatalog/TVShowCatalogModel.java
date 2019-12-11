package com.madd.madd.tmdb.Fragments.TVShowCatalog;

import com.madd.madd.tmdb.HTTP.Models.TVShowList;
import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.Utilities.References;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowCatalogModel implements TVShowCatalogContract.Model {

    TMDBApi tmdbApi;

    public TVShowCatalogModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }


    @Override
    public void getTVShowPopularList(int page, GetTVShowList tvShowList) {
        Call<TVShowList> tvShowListCall = tmdbApi.getTVShowPopularList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        tvShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                tvShowList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {
                tvShowList.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getTVShowTopRatedList(int page, GetTVShowList tvShowList) {
        Call<TVShowList> tvShowListCall = tmdbApi.getTVShowTopRatedList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        tvShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                tvShowList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {
                tvShowList.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getTVShowOnAirList(int page, GetTVShowList tvShowList) {
        Call<TVShowList> tvShowListCall = tmdbApi.getTVShowOnAirList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        tvShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                tvShowList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {
                tvShowList.onError(t.getLocalizedMessage());
            }
        });

    }
}
