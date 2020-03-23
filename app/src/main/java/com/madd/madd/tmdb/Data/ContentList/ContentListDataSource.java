package com.madd.madd.tmdb.Data.ContentList;

import com.madd.madd.tmdb.Data.ContentList.Model.ContentList;
import com.madd.madd.tmdb.Data.Movie.Model.Movie;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;
import com.madd.madd.tmdb.UI.ContentSearch.ContentSearchContract;

import java.util.List;

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
