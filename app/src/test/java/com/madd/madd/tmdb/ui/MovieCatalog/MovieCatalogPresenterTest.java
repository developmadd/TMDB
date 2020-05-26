package com.madd.madd.tmdb.ui.MovieCatalog;

import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataSource;
import com.madd.madd.tmdb.data.entities.Movie.MovieRepository;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieCatalogPresenterTest {

    private MovieCatalogPresenter presenter;

    private MovieRepository mockedRepository;
    private MovieCatalogContract.View mockedView;

    @Before
    public void setUp(){


        mockedRepository = mock(MovieRepository.class);
        mockedView = mock(MovieCatalogContract.View.class);

        presenter = new MovieCatalogPresenter(mockedRepository);
        presenter.setView(mockedView);

    }

    /*

        Tests:
        1: Request list without internet
        2: Request list with empty result
        3: Filter list
        4: Filter list with no results

     */

    @Test
    public void requestMovieListWithoutInternet(){

        when(mockedView.getListType()).thenReturn(MovieCatalogFragment.POPULAR_TYPE);
        doAnswer(invocation -> {
            ((DataSource.GetList)invocation.getArguments()[1]).onError("Sin internet");
            return null;
        }).when(mockedRepository).requestNextMoviePopularList(any(DataSource.GetList.class));

        presenter.requestMovieList();

        verify(mockedView).showInternetError();
    }

    @Test
    public void requestMovieListWithEmptyResult(){

        when(mockedView.getListType()).thenReturn(MovieCatalogFragment.POPULAR_TYPE);
        doAnswer(invocation -> {

            List<MovieList.Movie> emptyMovieList = new ArrayList<>();
            ((DataSource.GetList)invocation.getArguments()[1]).onSuccess(emptyMovieList);
            return null;
        }).when(mockedRepository).requestNextMoviePopularList(any(DataSource.GetList.class));

        presenter.requestMovieList();

        verify(mockedView).showEmptyListError();


    }


    @Test
    public void filterList(){

        List<MovieList.Movie> movieList = new ArrayList<>();
        movieList.add(new MovieList.Movie("","back to the future",""));
        movieList.add(new MovieList.Movie("","dragon ball",""));
        movieList.add(new MovieList.Movie("","transilvania",""));

        List<MovieList.Movie> filteredMovieList = new ArrayList<>();
        filteredMovieList.add(new MovieList.Movie("","back to the future",""));

        when(mockedView.getListType()).thenReturn(MovieCatalogFragment.POPULAR_TYPE);
        doAnswer(invocation -> {

            ((DataSource.GetList)invocation.getArguments()[1]).onSuccess(filteredMovieList);
            return null;
        }).when(mockedRepository).getFilteredPopularList(eq("back"),any(DataSource.GetList.class));

        verify(mockedView).hideError();
        verify(mockedView).showMovieList(refEq(filteredMovieList));
    }


    /*@Test
    public void filterListWithNoResults(){

        List<MovieList.Movie> movieList = new ArrayList<>();
        movieList.add(new MovieList.Movie("","back to the future",""));
        movieList.add(new MovieList.Movie("","dragon ball",""));
        movieList.add(new MovieList.Movie("","transilvania",""));

        when(mockedView.getMovieList()).thenReturn(movieList);

        presenter.filterMovieList("no coincidence :(");

        verify(mockedView).showEmptyListError();

    }*/
}