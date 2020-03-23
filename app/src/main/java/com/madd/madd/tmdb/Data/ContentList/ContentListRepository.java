package com.madd.madd.tmdb.Data.ContentList;

public class ContentListRepository implements ContentListDataSource.Repository {

    private ContentListDataSource.Remote contentListDataSourceRemote;

    public ContentListRepository(ContentListDataSource.Remote
                                         contentListDataSourceRemote) {
        this.contentListDataSourceRemote = contentListDataSourceRemote;
    }

    @Override
    public void getMovieListByQuery(String query, int page,
                                    ContentListDataSource.GetContentList getContentList) {
        contentListDataSourceRemote.getMovieListByQuery(query,page,getContentList);
    }

    @Override
    public void getTVShowListByQuery(String query, int page,
                                     ContentListDataSource.GetContentList getContentList) {
        contentListDataSourceRemote.getTVShowListByQuery(query,page,getContentList);
    }
}
