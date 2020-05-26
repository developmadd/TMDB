package com.madd.madd.tmdb.data.entities.ContentList;

import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.DataSource;

public interface ContentListDataSource {

    interface Repository {
        void getMovieListByQuery(String query, int page, DataSource.GetEntity<ContentList> getContentList);
        void getTVShowListByQuery(String query, int page, DataSource.GetEntity<ContentList> getContentList);
    }

    interface Remote{
        void getMovieListByQuery(String query, int page, DataSource.GetEntity<ContentList> getContentList);
        void getTVShowListByQuery(String query, int page, DataSource.GetEntity<ContentList> getContentList);

    }





}
