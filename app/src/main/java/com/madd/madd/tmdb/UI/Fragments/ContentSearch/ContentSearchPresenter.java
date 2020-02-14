package com.madd.madd.tmdb.UI.Fragments.ContentSearch;

import com.madd.madd.tmdb.HTTP.Models.ContentList;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.HTTP.Models.TVShowList;

import java.util.ArrayList;
import java.util.List;

public class ContentSearchPresenter implements ContentSearchContract.Presenter {


    private ContentSearchContract.Model model;
    private ContentSearchContract.View view;

    ContentSearchPresenter(ContentSearchContract.Model model) {
        this.model = model;
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
                model.getMovieListByQuery(query, view.getMoviePage(), new ContentSearchContract.Model.GetContentList() {
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

                model.getTVShowListByQuery(query, view.getTVShowPage(), new ContentSearchContract.Model.GetContentList() {
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
