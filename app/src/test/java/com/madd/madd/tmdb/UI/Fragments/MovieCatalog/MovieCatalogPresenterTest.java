package com.madd.madd.tmdb.UI.Fragments.MovieCatalog;

import com.madd.madd.tmdb.HTTP.Models.MovieList;

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

    private MovieCatalogContract.Model mockedModel;
    private MovieCatalogContract.View mockedView;

    @Before
    public void setUp(){

        mockedModel = mock(MovieCatalogContract.Model.class);
        mockedView = mock(MovieCatalogContract.View.class);

        presenter = new MovieCatalogPresenter(mockedModel);
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
            ((MovieCatalogContract.Model.GetMovieList)invocation.getArguments()[1]).onError("Sin internet");
            return null;
        }).when(mockedModel).getMoviePopularList(eq(0),any(MovieCatalogContract.Model.GetMovieList.class));

        presenter.requestMovieList();

        verify(mockedView).showInternetError();
    }

    @Test
    public void requestMovieListWithEmptyResult(){

        when(mockedView.getListType()).thenReturn(MovieCatalogFragment.POPULAR_TYPE);
        doAnswer(invocation -> {

            List<MovieList.Movie> emptyMovieList = new ArrayList<>();
            MovieList movieList = new MovieList(1,emptyMovieList);

            ((MovieCatalogContract.Model.GetMovieList)invocation.getArguments()[1]).onSuccess(movieList);
            return null;
        }).when(mockedModel).getMoviePopularList(eq(0),any(MovieCatalogContract.Model.GetMovieList.class));

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

        when(mockedView.getMovieList()).thenReturn(movieList);

        presenter.filterMovieList("back");

        verify(mockedView).hideError();
        verify(mockedView).showMovieList(refEq(filteredMovieList),eq(1));
    }


    @Test
    public void filterListWithNoResults(){

        List<MovieList.Movie> movieList = new ArrayList<>();
        movieList.add(new MovieList.Movie("","back to the future",""));
        movieList.add(new MovieList.Movie("","dragon ball",""));
        movieList.add(new MovieList.Movie("","transilvania",""));

        when(mockedView.getMovieList()).thenReturn(movieList);

        presenter.filterMovieList("no coincidence :(");

        verify(mockedView).showEmptyListError();

    }
}