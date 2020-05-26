package com.madd.madd.tmdb.ui.TVShowDetail;

import com.madd.madd.tmdb.data.entities.Cast.CastDataSource;
import com.madd.madd.tmdb.data.entities.Cast.CastRepository;
import com.madd.madd.tmdb.data.entities.DataSource;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShow;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataSource;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowRepository;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowDetailPresenterTest {


    private TVShowDetailPresenter presenter;

    private TVShowDataSource.Repository mockedTVRepository;
    private CastDataSource.Repository mockedCastRepository;
    private TVShowDetailContract.View mockedView;

    @Before
    public void setUp(){

        mockedTVRepository = mock(TVShowRepository.class);
        mockedCastRepository = mock(CastRepository.class);
        mockedView = mock(TVShowDetailContract.View.class);

        presenter = new TVShowDetailPresenter(mockedTVRepository,mockedCastRepository);
        presenter.setView(mockedView);


    }

    /*

        Tests:
        1: Request tv show without internet
        2: Load cast with error
        3: Load detail correctly

     */


    @Test
    public void showTVSHowWithoutInternet(){
        when(mockedView.getTVShowId()).thenReturn("tvShowId");
        doAnswer(invocation -> {
            ((DataSource.GetEntity)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedTVRepository).getTVShow(eq("tvShowId"),any(DataSource.GetEntity.class));

        presenter.getTVShow();
        verify(mockedView).showTVShowError();
    }

    @Test
    public void showMovieWitCastError(){
        when(mockedView.getTVShowId()).thenReturn("tvShowId");
        doAnswer(invocation -> {
            ((DataSource.GetEntity)invocation.getArguments()[1]).onError("sin internet");
            return null;
        }).when(mockedCastRepository).getTVShowCast(eq("tvShowId"),any(DataSource.GetEntity.class));

        presenter.getCast();
        verify(mockedView).showCastError();
    }
    @Test
    public void showTVShow(){
        TVShow requestedTVShow = new TVShow();
        requestedTVShow.setName("Smallville");
        when(mockedView.getTVShowId()).thenReturn("tvShowId");

        doAnswer(invocation -> {
            ((DataSource.GetEntity<TVShow>)invocation.getArguments()[1]).onSuccess(requestedTVShow);
            return null;
        }).when(mockedTVRepository).getTVShow(eq("tvShowId"),any(DataSource.GetEntity.class));

        presenter.getTVShow();
        verify(mockedView).showTVShow(requestedTVShow);
    }


}