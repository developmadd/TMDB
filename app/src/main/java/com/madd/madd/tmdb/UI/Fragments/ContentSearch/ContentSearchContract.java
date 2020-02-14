package com.madd.madd.tmdb.UI.Fragments.ContentSearch;

import com.madd.madd.tmdb.HTTP.Models.ContentList;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.HTTP.Models.TVShowList;

import java.util.List;

public interface ContentSearchContract {

    interface View{
        void showContentList(List<ContentList.Content> contentList, int moviePage, int tvShowPage);
        void clearContentList();
        void showEmptyListError();
        void showInternetError();
        void hideError();
        void openMovieDetail(MovieList.Movie movie);
        void openTVShowDetail(TVShowList.TVShow tvShow);

        List<ContentList.Content> getContentList();
        int getMoviePage();
        int getTVShowPage();

        interface MovieSelected {
            void onMovieClick(MovieList.Movie movie);
        }
        interface TVShowSelected {
            void onTVShowClick(TVShowList.TVShow tvShow);
        }
    }

    interface Presenter{
        void setView(ContentSearchContract.View view);

        void requestContentList(String query);
        void selectContent(ContentList.Content content);
        void clearContentList();
    }

    interface Model{
        void getMovieListByQuery(String query, int page, GetContentList getContentList);
        void getTVShowListByQuery(String query, int page, GetContentList getContentList);

        interface GetContentList{
            void onSuccess(ContentList contentList);
            void onError(String error);
        }

    }
}
