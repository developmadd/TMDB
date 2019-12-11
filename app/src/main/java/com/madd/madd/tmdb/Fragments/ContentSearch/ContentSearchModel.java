package com.madd.madd.tmdb.Fragments.ContentSearch;



import com.madd.madd.tmdb.HTTP.Models.ContentList;
import com.madd.madd.tmdb.HTTP.TMDBApi;
import com.madd.madd.tmdb.Utilities.References;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentSearchModel implements ContentSearchContract.Model {


    TMDBApi tmdbApi;

    public ContentSearchModel(TMDBApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    @Override
    public void getMovieListByQuery(String query, int page, GetContentList getContentList) {

        Call<ContentList> contentListCall = tmdbApi.getMovieListByQuery(
                References.TMDB_API_KEY,
                References.TMDB_LANGUAGE,
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
                References.TMDB_API_KEY,
                References.TMDB_LANGUAGE,
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
