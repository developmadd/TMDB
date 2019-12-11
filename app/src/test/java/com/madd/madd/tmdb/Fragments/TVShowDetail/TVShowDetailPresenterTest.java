package com.madd.madd.tmdb.Fragments.TVShowDetail;

import com.madd.madd.tmdb.Fragments.MovieDetail.MovieDetailContract;
import com.madd.madd.tmdb.Fragments.MovieDetail.MovieDetailPresenter;
import com.madd.madd.tmdb.HTTP.Models.Movie;
import com.madd.madd.tmdb.HTTP.Models.TVShow;
import com.madd.madd.tmdb.HTTP.Models.TVShowList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowDetailPresenterTest {


    private TVShowDetailPresenter presenter;

    private TVShowDetailContract.Model mockedModel;
    private TVShowDetailContract.View mockedView;

    @Before
    public void setUp(){

        mockedModel = mock(TVShowDetailContract.Model.class);
        mockedView = mock(TVShowDetailContract.View.class);

        presenter = new TVShowDetailPresenter(mockedModel);
        presenter.setView(mockedView);

    }

    /*

        Tests:
        1: Request tv show without internet
        2: Load cast with error
        3: Load detail correctly

     */


    @Test
    public void showMovieWithoutInternet(){
        when(mockedView.getTVShowId()).thenReturn("tvShowId");
        doAnswer(invocation -> {
            ((TVShowDetailContract.Model.GetTVShow)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedModel).getTVShow(eq("tvShowId"),any(TVShowDetailContract.Model.GetTVShow.class));

        presenter.getTVShow();
        verify(mockedView).showTVShowError();
    }

    @Test
    public void showMovieWitCastError(){
        when(mockedView.getTVShowId()).thenReturn("tvShowId");
        doAnswer(invocation -> {
            ((TVShowDetailContract.Model.GetCast)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedModel).getTVShowCast(eq("tvShowId"),any(TVShowDetailContract.Model.GetCast.class));

        presenter.getCast();
        verify(mockedView).showCastError();
    }
    @Test
    public void showMovie(){
        TVShow requestedTVShow = new TVShow();
        requestedTVShow.setName("Smallville");
        when(mockedView.getTVShowId()).thenReturn("tvShowId");

        doAnswer(invocation -> {
            ((TVShowDetailContract.Model.GetTVShow)invocation.getArguments()[1]).onSuccess(requestedTVShow);
            return null;
        }).when(mockedModel).getTVShow(eq("tvShowId"),any(TVShowDetailContract.Model.GetTVShow.class));

        presenter.getTVShow();
        verify(mockedView).showTVShow(requestedTVShow);
    }


}