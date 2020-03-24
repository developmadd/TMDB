package com.madd.madd.tmdb.ui.TVShowCatalog;

import com.madd.madd.tmdb.ui.MovieCatalog.MovieCatalogFragment;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;

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

public class TVShowCatalogPresenterTest {


    private TVShowCatalogPresenter presenter;

    private TVShowCatalogContract.Model mockedModel;
    private TVShowCatalogContract.View mockedView;

    @Before
    public void setUp(){

        mockedModel = mock(TVShowCatalogContract.Model.class);
        mockedView = mock(TVShowCatalogContract.View.class);

        presenter = new TVShowCatalogPresenter(mockedModel);
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

        when(mockedView.getListType()).thenReturn(TVShowCatalogFragment.POPULAR_TYPE);
        doAnswer(invocation -> {
            ((TVShowCatalogContract.Model.GetTVShowList)invocation.getArguments()[1]).onError("Sin internet");
            return null;
        }).when(mockedModel).getTVShowPopularList(eq(0),any(TVShowCatalogContract.Model.GetTVShowList.class));

        presenter.requestTVShowList();

        verify(mockedView).showInternetError();
    }

    @Test
    public void requestMovieListWithEmptyResult(){

        when(mockedView.getListType()).thenReturn(MovieCatalogFragment.POPULAR_TYPE);
        doAnswer(invocation -> {

            List<TVShowList.TVShow> emptyTVShowList = new ArrayList<>();
            TVShowList tvShowList = new TVShowList(1,emptyTVShowList);

            ((TVShowCatalogContract.Model.GetTVShowList)invocation.getArguments()[1]).onSuccess(tvShowList);
            return null;
        }).when(mockedModel).getTVShowPopularList(eq(0),any(TVShowCatalogContract.Model.GetTVShowList.class));

        presenter.requestTVShowList();

        verify(mockedView).showEmptyListError();


    }


    @Test
    public void filterList(){

        List<TVShowList.TVShow> tvShowList = new ArrayList<>();
        tvShowList.add(new TVShowList.TVShow("","simpsons",""));
        tvShowList.add(new TVShowList.TVShow("","dragon ball",""));
        tvShowList.add(new TVShowList.TVShow("","malcolm",""));

        List<TVShowList.TVShow> filteredMovieList = new ArrayList<>();
        filteredMovieList.add(new TVShowList.TVShow("","malcolm",""));

        when(mockedView.getTVShowList()).thenReturn(tvShowList);

        presenter.filterTVShowList("malc");

        verify(mockedView).hideError();
        verify(mockedView).showTVShowList(refEq(filteredMovieList),eq(1));
    }


    @Test
    public void filterListWithNoResults(){

        List<TVShowList.TVShow> tvShowList = new ArrayList<>();
        tvShowList.add(new TVShowList.TVShow("","simpsons",""));
        tvShowList.add(new TVShowList.TVShow("","dragon ball",""));
        tvShowList.add(new TVShowList.TVShow("","malcolm",""));

        when(mockedView.getTVShowList()).thenReturn(tvShowList);

        presenter.filterTVShowList("no coincidence :(");

        verify(mockedView).showEmptyListError();

    }
}