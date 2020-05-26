package com.madd.madd.tmdb.data.entities.ContentList;

import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.DataSource;

public class ContentListRepository implements ContentListDataSource.Repository {

    private ContentListDataSource.Remote contentListDataSourceRemote;

    public ContentListRepository(ContentListDataSource.Remote
                                         contentListDataSourceRemote) {
        this.contentListDataSourceRemote = contentListDataSourceRemote;
    }

    @Override
    public void getMovieListByQuery(String query, int page,
                                    DataSource.GetEntity<ContentList> getContentList) {
        contentListDataSourceRemote.getMovieListByQuery(query,page,getContentList);
    }

    @Override
    public void getTVShowListByQuery(String query, int page,
                                     DataSource.GetEntity<ContentList> getContentList) {
        contentListDataSourceRemote.getTVShowListByQuery(query,page,getContentList);
    }
}
