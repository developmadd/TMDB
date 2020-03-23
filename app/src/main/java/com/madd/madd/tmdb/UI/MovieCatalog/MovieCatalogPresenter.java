package com.madd.madd.tmdb.UI.MovieCatalog;

import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MovieCatalogPresenter implements MovieCatalogContract.Presenter {

    private MovieDataSource.Repository repository;
    private MovieCatalogContract.View view;

    private List<MovieList.Movie> movieList = new ArrayList<>();

    public MovieCatalogPresenter(MovieDataSource.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(MovieCatalogContract.View view) {
        this.view = view;
    }

    @Override
    public void requestMovieList() {
        if( view != null ){
            int page = view.getPage();

            MovieDataSource.GetMovieList getMovieList = new MovieDataSource.GetMovieList() {
                @Override
                public void onSuccess(MovieList movieList) {
                    if(!movieList.getMovieList().isEmpty()){
                        view.hideError();
                        view.showMovieList(movieList.getMovieList(),movieList.getPage() + 1);
                    } else {
                        view.showEmptyListError();
                    }
                }


                @Override
                public void onError(String error) {
                    view.showInternetError();
                }
            };

            if( view.getListType() == MovieCatalogFragment.POPULAR_TYPE ) {
                repository.getMoviePopularList(page, getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.TOP_RATED_TYPE ){
                repository.getMovieTopRatedList(page, getMovieList);
            } else if( view.getListType() == MovieCatalogFragment.UPCOMING_TYPE ){
                repository.getMovieUpcomingList(page, getMovieList);
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
