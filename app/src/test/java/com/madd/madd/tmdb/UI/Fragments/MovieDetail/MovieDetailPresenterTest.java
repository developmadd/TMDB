package com.madd.madd.tmdb.UI.Fragments.MovieDetail;

import com.madd.madd.tmdb.HTTP.Models.Movie;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailPresenterTest {

    private MovieDetailPresenter presenter;

    private MovieDetailContract.Model mockedModel;
    private MovieDetailContract.View mockedView;

    @Before
    public void setUp(){

        mockedModel = mock(MovieDetailContract.Model.class);
        mockedView = mock(MovieDetailContract.View.class);

        presenter = new MovieDetailPresenter(mockedModel);
        presenter.setView(mockedView);

    }

    /*

        Tests:
        1: Request movie without internet
        2: Load cast with error
        3: Load detail correctly


     */

    @Test
    public void showMovieWithoutInternet(){
        when(mockedView.getMovieId()).thenReturn("movieId");
        doAnswer(invocation -> {
            ((MovieDetailContract.Model.GetMovie)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedModel).getMovie(eq("movieId"),any(MovieDetailContract.Model.GetMovie.class));

        presenter.getMovie();
        verify(mockedView).showMovieError();
    }

    @Test
    public void showMovieWitCastError(){
        when(mockedView.getMovieId()).thenReturn("movieId");
        doAnswer(invocation -> {
            ((MovieDetailContract.Model.GetCast)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedModel).getMovieCast(eq("movieId"),any(MovieDetailContract.Model.GetCast.class));

        presenter.getCast();
        verify(mockedView).showCastError();
    }
    @Test
    public void showMovie(){
        Movie requestedMovie = new Movie();
        requestedMovie.setTitle("Wathmen");
        when(mockedView.getMovieId()).thenReturn("movieId");

        doAnswer(invocation -> {
            ((MovieDetailContract.Model.GetMovie)invocation.getArguments()[1]).onSuccess(requestedMovie);
            return null;
        }).when(mockedModel).getMovie(eq("movieId"),any(MovieDetailContract.Model.GetMovie.class));

        presenter.getMovie();
        verify(mockedView).showMovie(requestedMovie);
    }

}