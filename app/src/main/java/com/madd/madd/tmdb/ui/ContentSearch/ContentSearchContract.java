package com.madd.madd.tmdb.ui.ContentSearch;

import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

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

    }

    interface Presenter{
        void setView(ContentSearchContract.View view);

        void requestContentList(String query);
        void selectContent(ContentList.Content content);
        void clearContentList();
    }

}
