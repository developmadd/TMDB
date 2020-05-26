package com.madd.madd.tmdb.ui.ContentSearch;

import com.madd.madd.tmdb.data.entities.ContentList.ContentListDataSource;
import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

import java.util.ArrayList;
import java.util.List;

public class ContentSearchPresenter implements ContentSearchContract.Presenter {


    private ContentListDataSource.Repository repository;
    private ContentSearchContract.View view;

    ContentSearchPresenter(ContentListDataSource.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(ContentSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void requestContentList(String query) {
        if( view != null ){

            if(!query.isEmpty()) {
                List<ContentList.Content> joinContentList = new ArrayList<>();
                final int[] counter = {0}, moviePage = {0}, tvShowPage = {0};
                repository.getMovieListByQuery(query, view.getMoviePage(),
                        new DataSource.GetEntity<ContentList>() {
                    @Override
                    public void onSuccess(ContentList contentList) {

                        joinContentList.addAll(contentList.getContentList());
                        moviePage[0] = contentList.getPage() + 1;

                        if (++counter[0] == 2) {
                            if( joinContentList.isEmpty()){
                                view.showEmptyListError();
                            } else {
                                view.showContentList(joinContentList, moviePage[0], tvShowPage[0]);
                            }
                        }
                    }

                    @Override
                    public void onError(String error) {
                        view.showInternetError();
                    }
                });

                repository.getTVShowListByQuery(query, view.getTVShowPage(),
                        new DataSource.GetEntity<ContentList>() {
                    @Override
                    public void onSuccess(ContentList contentList) {
                        joinContentList.addAll(contentList.getContentList());
                        tvShowPage[0] = contentList.getPage() + 1;

                        if (++counter[0] == 2) {
                            if( joinContentList.isEmpty()){
                                view.showEmptyListError();
                            } else {
                                view.showContentList(joinContentList, moviePage[0], tvShowPage[0]);
                            }
                        }
                    }

                    @Override
                    public void onError(String error) {
                        view.showInternetError();
                    }
                });
            } else {
                clearContentList();
            }

        }
    }




    @Override
    public void selectContent(ContentList.Content content) {
        if( view != null ){
            if( content.isMovie() ){
                MovieList.Movie movie = new MovieList.Movie();
                movie.setId(content.getId());
                view.openMovieDetail(movie);
            } else {
                TVShowList.TVShow tvShow = new TVShowList.TVShow();
                tvShow.setId(content.getId());
                view.openTVShowDetail(tvShow);
            }
        }
    }

    @Override
    public void clearContentList() {
        if( view != null ){
            view.clearContentList();
        }
    }
}
