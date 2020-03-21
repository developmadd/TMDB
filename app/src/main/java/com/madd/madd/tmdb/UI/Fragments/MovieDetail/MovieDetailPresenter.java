package com.madd.madd.tmdb.UI.Fragments.MovieDetail;

import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.HTTP.Models.Movie;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.Model model;
    private MovieDetailContract.View view;

    public MovieDetailPresenter(MovieDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MovieDetailContract.View view) {
        this.view = view;
    }


    @Override
    public void getMovie() {
        if( view != null ) {
            view.showLoadingProgress();
            model.getMovie(view.getMovieId(), new MovieDetailContract.Model.GetMovie() {
                @Override
                public void onSuccess(Movie movie) {
                    view.showMovie(movie);
                    view.hideLoadingProgress();
                }

                @Override
                public void onError(String error) {
                    view.showMovieError();
                    view.hideLoadingProgress();
                }
            });

        }
    }

    @Override
    public void getCast() {
        if( view != null ){

            model.getMovieCast(view.getMovieId(), new MovieDetailContract.Model.GetCast() {
                @Override
                public void onSuccess(Cast cast) {
                    if(!cast.getActorList().isEmpty() ) {
                        view.showCast(cast);
                    } else{
                        view.showCastError();
                    }
                }

                @Override
                public void onError(String error) {
                    view.showCastError();
                }
            });

        }
    }
}
