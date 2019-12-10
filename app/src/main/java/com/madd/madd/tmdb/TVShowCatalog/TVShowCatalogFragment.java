package com.madd.madd.tmdb.TVShowCatalog;


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

import com.madd.madd.tmdb.DI.App;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.HTTP.Models.TVShowList;
import com.madd.madd.tmdb.Models.Movie_TVShowList;
import com.madd.madd.tmdb.MovieCatalog.MovieAdapter;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogContract;
import com.madd.madd.tmdb.R;
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
public class TVShowCatalogFragment extends Fragment implements TVShowCatalogContract.View {




    public static final int POPULAR_TYPE = 0;
    public static final int UPCOMING_TYPE = 1;
    public static final int TOP_RATED_TYPE = 2;


    @Inject
    TVShowCatalogContract.Presenter presenter;

    @BindView(R.id.CTNR_TVShow_Catalog)  RecyclerView recyclerView;
    @BindView(R.id.SV_TVShow_Catalog)  SearchView searchView;
    @BindView(R.id.TV_TVShow_Catalog_Empty)  TextView textViewEmpty;


    private TVShowAdapter tvShowAdapter;


    private List<TVShowList.TVShow> tvShowList = new ArrayList<>();
    int page = 1;
    boolean searchBarAnimationStatus = false;
    private TVShowCatalogContract.View.TVShowSelected onTVShowSelected;
    int listType;


    public void setListType(int listType) {
        this.listType = listType;
    }
    public void setOnTVShowSelected(TVShowCatalogContract.View.TVShowSelected onTVShowSelected) {
        this.onTVShowSelected = onTVShowSelected;
    }



    public TVShowCatalogFragment() {

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvshow_catalog, container, false);
        ButterKnife.bind(this,view);
        ((App) Objects.requireNonNull(getActivity()).getApplication()).getComponent().inject(this);

        loadView();


        presenter.setView(this);
        presenter.requestTVShowList();
        return view;

    }







    private void loadView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        tvShowAdapter = new TVShowAdapter(tvShowList, new TVShowAdapter.TVShowEvents() {
            @Override
            public void onTVShowClick(TVShowList.TVShow selectedTVShow) {
                presenter.selectTVShow(selectedTVShow);
            }

            @Override
            public void onRequestNextPage() {
                presenter.requestTVShowList();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(tvShowAdapter);

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
    public void showTVShowList(List<TVShowList.TVShow> tvShowList, int page) {
        int fromIndex = this.movieList.size();
        int toIndex = this.movieList.size() + movieList.size();
        this.movieList.addAll(movieList);
        this.page = page;
        movieAdapter.notifyItemRangeInserted(fromIndex, toIndex);
    }

    @Override
    public void clearTVShowList() {
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
    public void openTVShowDetail(TVShowList.TVShow tvShow) {
        onMovieSelected.onMovieClick(tvShow);
    }

    @Override
    public List<TVShowList.TVShow> getTVShowList() {
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
