package com.madd.madd.tmdb.Fragments.Movie;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madd.madd.tmdb.Models.Lists.Content.ContentList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.TabAdapter;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSearch extends Fragment {



    private View view;

    private MovieCatalog moviePopularList;
    private MovieCatalog movieUpcomingList;
    private MovieCatalog movieTopRateList;

    private ContentList.OnContentSelected onMovieSelected;
    public void setOnMovieSelected(ContentList.OnContentSelected onMovieSelected) {
        this.onMovieSelected = onMovieSelected;
    }

    public MovieSearch() {
        // Required empty public constructor
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null){
            view = inflater.inflate(R.layout.fragment_movie_search, container, false);
            createMovieLists();
            bindUI();
        }

        return view;
    }


    private void createMovieLists(){

        moviePopularList = new MovieCatalog();
        moviePopularList.setListType(References.POPULAR_TYPE);
        moviePopularList.setOnMovieSelected(onMovieSelected);

        movieUpcomingList = new MovieCatalog();
        movieUpcomingList.setListType(References.UPCOMING_TYPE);
        movieUpcomingList.setOnMovieSelected(onMovieSelected);

        movieTopRateList = new MovieCatalog();
        movieTopRateList.setListType(References.TOP_RATED_TYPE);
        movieTopRateList.setOnMovieSelected(onMovieSelected);


    }











    private void bindUI(){

        TabLayout tabLayout = view.findViewById(R.id.TAB_Movie_Search);
        ViewPager viewPager = view.findViewById(R.id.VP_Movie_Search);

        TabAdapter tabAdapter = new TabAdapter(getFragmentManager());

        tabAdapter.addFragment(moviePopularList,"Más populares");
        tabAdapter.addFragment(movieTopRateList,"Mejor calificadas");
        tabAdapter.addFragment(movieUpcomingList,"Próximo lanzamiento");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorGray),
                ContextCompat.getColor(getContext(), R.color.colorWhite));

        Utilities.hideKeyboardFromTab(tabLayout);

    }






}
