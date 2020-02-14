package com.madd.madd.tmdb.UI.Fragments.ContentSearch;



import com.madd.madd.tmdb.HTTP.Models.ContentList;
import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.HTTP.TMDBModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentSearchModel implements ContentSearchContract.Model {


    private TMDBApi tmdbApi;

    ContentSearchModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    @Override
    public void getMovieListByQuery(String query, int page, GetContentList getContentList) {

        Call<ContentList> contentListCall = tmdbApi.getMovieListByQuery(
                TMDBModule.TMDB_API_KEY,
                TMDBModule.TMDB_LANGUAGE,
                query,page);
        contentListCall.enqueue(new Callback<ContentList>() {
            @Override
            public void onResponse(Call<ContentList> call, Response<ContentList> response) {
                getContentList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ContentList> call, Throwable t) {
                getContentList.onError(t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void getTVShowListByQuery(String query, int page, GetContentList getContentList) {

        Call<ContentList> contentListCall = tmdbApi.getTVShowListByQuery(
                TMDBModule.TMDB_API_KEY,
                TMDBModule.TMDB_LANGUAGE,
                query,page);
        contentListCall.enqueue(new Callback<ContentList>() {
            @Override
            public void onResponse(Call<ContentList> call, Response<ContentList> response) {
                getContentList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ContentList> call, Throwable t) {
                getContentList.onError(t.getLocalizedMessage());
            }
        });

    }


}
