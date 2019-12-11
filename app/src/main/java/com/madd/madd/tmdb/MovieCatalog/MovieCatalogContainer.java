package com.madd.madd.tmdb.MovieCatalog;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madd.madd.tmdb.DI.App;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.TabAdapter;
import com.madd.madd.tmdb.Utilities.Utilities;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogContainer extends Fragment {

    @BindView(R.id.TAB_Movie_Search) TabLayout tabLayout;
    @BindView(R.id.VP_Movie_Search) ViewPager viewPager;


    private MovieCatalogFragment moviePopularCatalog;
    private MovieCatalogFragment movieUpcomingCatalog;
    private MovieCatalogFragment movieTopRateCatalog;


    private MovieCatalogContract.View.MovieSelected onMovieSelected;
    public void setOnMovieSelected(MovieCatalogContract.View.MovieSelected onMovieSelected) {
        this.onMovieSelected = onMovieSelected;
    }

    public MovieCatalogContainer() {

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_search, container, false);
        ButterKnife.bind(this,view);

        createMovieCatalogs();
        setViewPager();


        return view;
    }


    private void createMovieCatalogs(){

        moviePopularCatalog = new MovieCatalogFragment();
        moviePopularCatalog.setListType(MovieCatalogFragment.POPULAR_TYPE);
        moviePopularCatalog.setOnMovieSelected(onMovieSelected);

        movieUpcomingCatalog = new MovieCatalogFragment();
        movieUpcomingCatalog.setListType(MovieCatalogFragment.UPCOMING_TYPE);
        movieUpcomingCatalog.setOnMovieSelected(onMovieSelected);

        movieTopRateCatalog = new MovieCatalogFragment();
        movieTopRateCatalog.setListType(MovieCatalogFragment.TOP_RATED_TYPE);
        movieTopRateCatalog.setOnMovieSelected(onMovieSelected);


    }











    private void setViewPager(){

        TabAdapter tabAdapter = new TabAdapter(getFragmentManager());

        tabAdapter.addFragment(moviePopularCatalog,"Más populares");
        tabAdapter.addFragment(movieTopRateCatalog,"Mejor calificadas");
        tabAdapter.addFragment(movieUpcomingCatalog,"Próximo lanzamiento");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.colorGray),
                ContextCompat.getColor(getContext(), R.color.colorWhite));

        Utilities.hideKeyboardFromTab(tabLayout);

    }








}
