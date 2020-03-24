package com.madd.madd.tmdb.ui.ContentSearch;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madd.madd.tmdb.di.App;
import com.madd.madd.tmdb.data.entities.ContentList.Model.ContentList;
import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.data.entities.TVShow.Model.TVShowList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.ui.MovieDetail.MovieDetailActivity;
import com.madd.madd.tmdb.ui.TVShowDetail.TVShowDetailActivity;
import com.madd.madd.tmdb.utilities.Utilities;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentSearchFragment extends Fragment implements ContentSearchContract.View {

    @Inject ContentSearchContract.Presenter presenter;

    @BindView(R.id.CTNR_General_Search) RecyclerView recyclerView;
    @BindView(R.id.SV_General_Search) SearchView searchView;
    @BindView(R.id.TV_General_Search_Empty) TextView textViewError;

    private ContentAdapter contentAdapter;




    boolean searchBarAnimationStatus = false;
    private CountDownTimer countDownTimer;






    public ContentSearchFragment() {

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general_search, container, false);
        ((App)requireActivity().getApplication()).getComponent().inject(this);
        ButterKnife.bind(this,view);
        loadView();

        presenter.setView(this);
        presenter.clearContentList();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }



    private void loadView(){



        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        contentAdapter = new ContentAdapter( new ContentAdapter.ContentEvents() {
            @Override
            public void onContentClick(ContentList.Content selectedContent) {
                presenter.selectContent(selectedContent);
            }

            @Override
            public void onRequestNextPage() {
                presenter.requestContentList(searchView.getQuery().toString());
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(contentAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                countDownTimer = new CountDownTimer(400, 100) {
                    public void onTick(long millisUntilFinished) {}
                    public void onFinish() {
                        presenter.clearContentList();
                        presenter.requestContentList(query);
                        if( query.isEmpty() ) {
                            Utilities.hideKeyboardFrom(searchView);
                        }

                    }
                }.start();

                return false;
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // No realizar animaciones mientras una se lleva a cabo
                if ( !searchBarAnimationStatus ) {

                    int firstVisibleItem = ((LinearLayoutManager)
                            Objects.requireNonNull(recyclerView.getLayoutManager()))
                            .findFirstVisibleItemPosition();

                    if (dy > 0 && firstVisibleItem != 0) {
                        Utilities.animateTranslationY(searchView, -40, status -> {
                            searchBarAnimationStatus = status;
                        });
                        Utilities.animateTranslationY(recyclerView,0,null);
                    } else if (dy < 0) {
                        Utilities.animateTranslationY(searchView, 0, status -> {
                            searchBarAnimationStatus = status;
                        });
                        Utilities.animateTranslationY(recyclerView,40,null);
                    }

                }

            }
        });


    }
































    @Override
    public void showContentList(List<ContentList.Content> contentList, int moviePage, int tvShowPage) {
        textViewError.setVisibility(View.GONE );
        textViewError.setText("");
        contentAdapter.getList().addAll(contentList);
        contentAdapter.setMoviePage(moviePage);
        contentAdapter.setTvShowPage(tvShowPage);
        contentAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearContentList() {
        textViewError.setVisibility(View.VISIBLE );
        textViewError.setText("Introduzca nombre de película o serie ");
        contentAdapter.getList().clear();
        contentAdapter.setMoviePage(1);
        contentAdapter.setTvShowPage(1);
        contentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyListError() {
        textViewError.setVisibility(View.VISIBLE);
        textViewError.setText("Sin resultados");
    }

    @Override
    public void showInternetError() {
        textViewError.setVisibility(View.VISIBLE);
        textViewError.setText("Sin conexión a Internet");
    }

    @Override
    public void hideError() {
        textViewError.setVisibility(View.GONE);
        textViewError.setText("");
    }




    @Override
    public void openMovieDetail(MovieList.Movie movie) {
        Intent intent = new Intent();
        intent.setClass(requireContext(), MovieDetailActivity.class);
        intent.putExtra("movieId",movie.getId());
        startActivity(intent);
    }

    @Override
    public void openTVShowDetail(TVShowList.TVShow tvShow) {
        Intent intent = new Intent();
        intent.setClass(requireContext(), TVShowDetailActivity.class);
        intent.putExtra("tvShowId",tvShow.getId());
        startActivity(intent);
    }


    @Override
    public List<ContentList.Content> getContentList() {
        return contentAdapter.getList();
    }

    @Override
    public int getMoviePage() {
        return contentAdapter.getMoviePage();
    }

    @Override
    public int getTVShowPage() {
        return contentAdapter.getTvShowPage();
    }




}
