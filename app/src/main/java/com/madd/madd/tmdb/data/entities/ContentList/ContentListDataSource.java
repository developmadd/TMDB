package com.madd.madd.tmdb.data.entities.ContentList;

import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;

public interface ContentListDataSource {

    interface Repository {
        void getMovieListByQuery(String query, int page, GetContentList getContentList);
        void getTVShowListByQuery(String query, int page, GetContentList getContentList);
    }

    interface Remote{
        void getMovieListByQuery(String query, int page, GetContentList getContentList);
        void getTVShowListByQuery(String query, int page, GetContentList getContentList);

    }



    interface GetContentList{
        void onSuccess(ContentList contentList);
        void onError(String error);
    }

}
