package com.madd.madd.tmdb.MovieCatalog;

import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MovieCatalogPresenter implements MovieCatalogContract.Presenter {

    private MovieCatalogContract.Model model;
    private MovieCatalogContract.View view;

    private List<MovieList.Movie> movieList = new ArrayList<>();

    public MovieCatalogPresenter(MovieCatalogContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MovieCatalogContract.View view) {
        this.view = view;
    }

    @Override
    public void requestMovieList() {
        if( view != null ){
            int page = view.getPage();

            MovieCatalogContract.Model.GetMovieList getMovieList = new MovieCatalogContract.Model.GetMovieList() {
                @Override
                public void onSuccess(MovieList movieList) {
                    view.showMovieList(movieList.getMovieList(),movieList.getPage() + 1);
                    if(!movieList.getMovieList().isEmpty()){
                        view.hideError();
                        view.showMovieList(movieList.getMovieList(),movieList.getPage() + 1);
                    } else {
                        view.showEmptyListError();
                    }
                }

                @Override
                public void onError(String error) {
                    view.showEmptyListError();
                }
            };

            if( view.getListType() == MovieCatalogFragment.POPULAR_TYPE ) {
                model.getMovieList(page, getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.TOP_RATED_TYPE ){
                model.getMovieTopRatedList(page, getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.UPCOMING_TYPE ){
                model.getMovieUpcomingList(page, getMovieList);
            }

        }
    }

    @Override
    public void filterMovieList(String query) {


        if( movieList.isEmpty() ) {
            movieList = new ArrayList<>(view.getMovieList());
        }

        List<MovieList.Movie> filteredMovieList = new ArrayList<>();
        view.clearMovieList();

        if (!query.isEmpty()) {
            String compare = Utilities.cleanString(query);
            for (MovieList.Movie movie : movieList) {

                String original = Utilities.cleanString(movie.getTitle());
                if (original.startsWith(compare)) {
                    filteredMovieList.add(movie);
                }
            }
            for (MovieList.Movie movie : movieList) {

                String original = Utilities.cleanString(movie.getTitle());
                if ( !original.startsWith(compare) && original.contains(compare) ) {
                    filteredMovieList.add(movie);
                }
            }
            if(!filteredMovieList.isEmpty()){
                view.hideError();
                view.showMovieList(filteredMovieList,1);
            } else {
                view.showEmptyListError();
            }

        } else {
            movieList.clear();
            requestMovieList();
        }

    }

    @Override
    public void selectMovie( MovieList.Movie movie ) {
        if( view != null ){
            view.openMovieDetail(movie);
        }
    }
}
