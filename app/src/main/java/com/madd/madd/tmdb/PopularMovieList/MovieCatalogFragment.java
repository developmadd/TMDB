package com.madd.madd.tmdb.PopularMovieList;


import android.os.Bundle;
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

import com.madd.madd.tmdb.Models.MovieList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Root.App;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.ArrayList;
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

    @BindView(R.id.CTNR_Movie_Catalog)  RecyclerView recyclerView;
    @BindView(R.id.SV_Movie_Catalog)  SearchView searchView;
    @BindView(R.id.TV_Movie_Catalog_Empty)  TextView textViewEmpty;


    private MovieAdapter movieAdapter;


    private List<MovieList.Movie> movieList = new ArrayList<>();
    int page = 1;
    boolean searchBarAnimationStatus = false;
    private MovieSelected onMovieSelected;
    int listType;


    public void setListType(int listType) {
        this.listType = listType;
    }
    public void setOnMovieSelected(MovieSelected onMovieSelected) {
        this.onMovieSelected = onMovieSelected;
    }



    public MovieCatalogFragment() {

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_catalog, container, false);
        ButterKnife.bind(this,view);
        ((App) Objects.requireNonNull(getActivity()).getApplication()).getComponent().inject(this);

        loadView();


        presenter.setView(this);
        presenter.requestMovieList();
        return view;

    }







    private void loadView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieAdapter = new MovieAdapter(movieList, new MovieAdapter.MovieEvents() {
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
                if( query.isEmpty() ){
                    Utilities.hideKeyboardFrom(searchView);
                }
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
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }


    @Override
    public void showMovieList(List<MovieList.Movie> movieList, int page) {
        int fromIndex = this.movieList.size();
        int toIndex = this.movieList.size() + movieList.size();
        this.movieList.addAll(movieList);
        this.page = page;
        movieAdapter.notifyItemRangeInserted(fromIndex, toIndex);
    }

    @Override
    public void clearMovieList() {
        this.movieList.clear();
        this.page = 1;
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyListError() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyListError() {
        textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void openMovieDetail(MovieList.Movie movie) {
        onMovieSelected.onMovieClick(movie);
    }

    @Override
    public List<MovieList.Movie> getMovieList() {
        return movieList;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getListType() {
        return listType;
    }


}
