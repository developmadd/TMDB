package com.madd.madd.tmdb.data.entities.TVShow.Source.Remote;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.http.TMDBApi;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowRemoteDataSource implements TVShowDataSource.Remote {

    private TMDBApi api;

    public TVShowRemoteDataSource(TMDBApi api) {
        this.api = api;
    }








    @Override
    public void getTVShow(String tvShowId, DataSource.GetEntity<TVShow> getTVShow) {
        Call<TVShow> tvShowCall = api.getTVShow(tvShowId,
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE);
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
    public void getTVShowPopularList(int page, DataSource.GetEntity<TVShowList> getTVShowList) {
        Call<TVShowList> tvShowListCall = api.getTVShowPopularList(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
                page);

        tvShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                getTVShowList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {
                getTVShowList.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getTVShowTopRatedList(int page, DataSource.GetEntity<TVShowList> getTVShowList) {
        Call<TVShowList> tvShowListCall = api.getTVShowTopRatedList(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
                page);

        tvShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                getTVShowList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {
                getTVShowList.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getTVShowOnAirList(int page, DataSource.GetEntity<TVShowList> getTVShowList) {
        Call<TVShowList> tvShowListCall = api.getTVShowOnAirList(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
                page);

        tvShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                getTVShowList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {
                getTVShowList.onError(t.getLocalizedMessage());
            }
        });

    }
}
