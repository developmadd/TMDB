package com.madd.madd.tmdb.ui.MovieDetail;

import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.Movie.Model.Movie;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataSource;

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

    private MovieDataSource.Repository mockedMovieRepository;
    private CastDataSource.Repository mockedCastRepository;
    private MovieDetailContract.View mockedView;

    @Before
    public void setUp(){

        mockedMovieRepository = mock(MovieDataSource.Repository.class);
        mockedCastRepository = mock(CastDataSource.Repository.class);
        mockedView = mock(MovieDetailContract.View.class);

        presenter = new MovieDetailPresenter(mockedMovieRepository,mockedCastRepository);
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
            ((DataSource.GetEntity)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedMovieRepository).getMovie(eq("movieId"),any(DataSource.GetEntity.class));

        presenter.getMovie();
        verify(mockedView).showMovieError();
    }

    @Test
    public void showMovieWitCastError(){
        when(mockedView.getMovieId()).thenReturn("movieId");
        doAnswer(invocation -> {
            ((DataSource.GetEntity)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedCastRepository).getMovieCast(eq("movieId"),any(DataSource.GetEntity.class));

        presenter.getCast();
        verify(mockedView).showCastError();
    }
    @Test
    public void showMovie(){
        Movie requestedMovie = new Movie();
        requestedMovie.setTitle("Wathmen");
        when(mockedView.getMovieId()).thenReturn("movieId");

        doAnswer(invocation -> {
            ((DataSource.GetEntity)invocation.getArguments()[1]).onSuccess(requestedMovie);
            return null;
        }).when(mockedMovieRepository).getMovie(eq("movieId"),any(DataSource.GetEntity.class));

        presenter.getMovie();
        verify(mockedView).showMovie(requestedMovie);
    }

}