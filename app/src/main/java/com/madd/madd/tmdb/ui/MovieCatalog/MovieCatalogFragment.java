package com.madd.madd.tmdb.ui.MovieCatalog;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madd.madd.tmdb.data.entities.Movie.Model.MovieList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.di.App;
import com.madd.madd.tmdb.ui.MovieDetail.MovieDetailActivity;
import com.madd.madd.tmdb.utilities.Utilities;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogFragment extends Fragment implements MovieCatalogContract.View{



    public static final int POPULAR_TYPE = 0;
    public static final int UPCOMING_TYPE = 1;
    public static final int TOP_RATED_TYPE = 2;


    @Inject MovieCatalogContract.Presenter presenter;
    private View view;
    @BindView(R.id.CTNR_Movie_Catalog)  RecyclerView recyclerView;
    @BindView(R.id.SV_Movie_Catalog)  SearchView searchView;
    @BindView(R.id.TV_Movie_Catalog_Empty)  TextView textViewEmpty;
    @BindView(R.id.SW_Movie_Catalog)  SwipeRefreshLayout swipeRefreshLayout;

    private MovieAdapter movieAdapter;


    boolean searchBarAnimationStatus = false;
    int listType;


    public void setListType(int listType) {
        this.listType = listType;
    }



    public MovieCatalogFragment() {

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if( view == null ) {
            view = inflater.inflate(R.layout.fragment_movie_catalog, container, false);
            ButterKnife.bind(this, view);
            ((App) Objects.requireNonNull(getActivity()).getApplication()).getComponent().inject(this);

            loadView();


            presenter.setView(this);
            presenter.requestMovieList();
        }
        return view;

    }







    private void loadView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieAdapter = new MovieAdapter( new MovieAdapter.MovieEvents() {
            @Override
            public void onMovieClick(MovieList.Movie selectedMovie) {
                presenter.selectMovie(selectedMovie);
            }

            @Override
            public void onRequestNextPage() {
                presenter.requestMovieList();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(movieAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                presenter.filterMovieList(query);
                if( query.isEmpty() ) {
                    Utilities.hideKeyboardFrom(searchView);
                }
                return false;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.refreshMovieList();
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
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }


    @Override
    public void showMovieList(List<MovieList.Movie> movieList) {
        int fromIndex = movieAdapter.getList().size();
        int toIndex = movieAdapter.getList().size() + movieList.size();
        movieAdapter.getList().addAll(movieList);
        movieAdapter.notifyItemRangeInserted(fromIndex, toIndex);
    }

    @Override
    public void clearMovieList() {
        movieAdapter.getList().clear();
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyListError() {
        textViewEmpty.setVisibility(View.VISIBLE);
        textViewEmpty.setText("Sin resultados");
    }

    @Override
    public void showInternetError() {
        textViewEmpty.setVisibility(View.VISIBLE);
        textViewEmpty.setText("Sin conexión a internet");
    }

    @Override
    public void hideError() {
        textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void openMovieDetail(MovieList.Movie movie) {
        Intent intent = new Intent();
        intent.putExtra("movieId",movie.getId());
        intent.setClass(requireContext(), MovieDetailActivity.class);
        startActivity(intent);
    }



    @Override
    public int getListType() {
        return listType;
    }


}
