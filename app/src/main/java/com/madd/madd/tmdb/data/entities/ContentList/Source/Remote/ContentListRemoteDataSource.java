package com.madd.madd.tmdb.data.entities.ContentList.Source.Remote;

import com.madd.madd.tmdb.data.entities.ContentList.ContentListDataSource;
import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.http.TMDBApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentListRemoteDataSource implements ContentListDataSource.Remote {



    private TMDBApi api;

    public ContentListRemoteDataSource(TMDBApi api) {
        this.api = api;
    }

    @Override
    public void getMovieListByQuery(String query, int page,
                                    DataSource.GetEntity<ContentList> getContentList) {

        Call<ContentList> contentListCall = api.getMovieListByQuery(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
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
    public void getTVShowListByQuery(String query, int page,
                                     DataSource.GetEntity<ContentList> getContentList) {

        Call<ContentList> contentListCall = api.getTVShowListByQuery(
                TMDBApi.TMDB_API_KEY,
                TMDBApi.TMDB_LANGUAGE,
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
