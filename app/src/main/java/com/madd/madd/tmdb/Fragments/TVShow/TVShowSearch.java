package com.madd.madd.tmdb.Fragments.TVShow;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madd.madd.tmdb.MovieCatalog.MovieCatalogContract;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.TVShowCatalog.TVShowCatalogFragment;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.TabAdapter;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowSearch extends Fragment {


    private View view;

    private TVShowCatalogFragment tvShowPopularList;
    private TVShowCatalogFragment tvShowOnAirList;
    private TVShowCatalogFragment tvShowTopRatedList;

    private MovieCatalogContract.View.MovieSelected onTVShowSelected;
    public void setOnTVShowSelected(MovieCatalogContract.View.MovieSelected onTVShowSelected) {
        this.onTVShowSelected = onTVShowSelected;
    }

    public TVShowSearch() {
        // Required empty public constructor
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null){
            view = inflater.inflate(R.layout.fragment_tvshow_search, container, false);
            createTVShowLists();
            bindUI();
        }

        return view;
    }


    private void createTVShowLists(){

        tvShowPopularList = new TVShowCatalogFragment();
        tvShowPopularList.setListType(References.POPULAR_TYPE);
        tvShowPopularList.setOnTVShowSelected(onTVShowSelected);

        tvShowOnAirList = new TVShowCatalogFragment();
        tvShowOnAirList.setListType(References.ON_AIR_TYPE);
        tvShowOnAirList.setOnTVShowSelected(onTVShowSelected);

        tvShowTopRatedList = new TVShowCatalogFragment();
        tvShowTopRatedList.setListType(References.TOP_RATED_TYPE);
        tvShowTopRatedList.setOnTVShowSelected(onTVShowSelected);


    }











    private void bindUI(){

        TabLayout tabLayout = view.findViewById(R.id.TAB_TVShow_Search);
        ViewPager viewPager = view.findViewById(R.id.VP_TVShow_Search);

        TabAdapter tabAdapter = new TabAdapter(getFragmentManager());

        tabAdapter.addFragment(tvShowPopularList,"Más populares");
        tabAdapter.addFragment(tvShowTopRatedList,"Mejor calificadas");
        tabAdapter.addFragment(tvShowOnAirList,"En televisión");


        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorGray),
                ContextCompat.getColor(getContext(), R.color.colorWhite));
        Utilities.hideKeyboardFromTab(tabLayout);

    }




}
