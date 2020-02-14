package com.madd.madd.tmdb.UI.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.madd.madd.tmdb.UI.Fragments.ContentSearch.ContentSearchFragment;
import com.madd.madd.tmdb.UI.Fragments.MovieCatalogContainerFragment;
import com.madd.madd.tmdb.UI.Fragments.MovieDetail.MovieDetailFragment;
import com.madd.madd.tmdb.UI.Fragments.TVShowCatalogContainerFragment;
import com.madd.madd.tmdb.UI.Fragments.TVShowDetail.TVShowDetailFragment;
import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.HTTP.Models.TVShowList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.DI.App;
import com.madd.madd.tmdb.Utilities.TabAdapter;
import com.madd.madd.tmdb.Utilities.Utilities;

public class MainActivity extends AppCompatActivity {


    private FrameLayout detailContainer;

    private MovieCatalogContainerFragment movieCatalogContainerFragment = new MovieCatalogContainerFragment();
    private TVShowCatalogContainerFragment tvShowCatalogContainerFragment = new TVShowCatalogContainerFragment();
    private ContentSearchFragment contentSearchFragment = new ContentSearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App)getApplication()).getComponent().inject(this);
        bindUI();
        setEvents();
    }









    private void setEvents(){

        movieCatalogContainerFragment.setOnMovieSelected(this::showMovieDetail);

        tvShowCatalogContainerFragment.setOnTVShowSelected(this::showTVShowDetail);

        contentSearchFragment.setOnMovieSelected(this::showMovieDetail);
        contentSearchFragment.setOnTVShowSelected(this::showTVShowDetail);

    }

    private void showTVShowDetail(TVShowList.TVShow tvShow){
        TVShowDetailFragment tvShowDetailFragment = new TVShowDetailFragment();
        tvShowDetailFragment.setTVShowId(tvShow.getId());
        tvShowDetailFragment.setOnTvShowDetailClose(this::hideDetail);

        getSupportFragmentManager().beginTransaction().replace(R.id.CTNR_Detail, tvShowDetailFragment)
                .commit();
        showDetail();

    }
    private void showMovieDetail(MovieList.Movie movie){
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setMovieId(movie.getId());
        movieDetailFragment.setOnMovieDetailClose(this::hideDetail);

        getSupportFragmentManager().beginTransaction().replace(R.id.CTNR_Detail, movieDetailFragment)
                .commit();
        showDetail();
    }


    public void showDetail() {

        detailContainer.setAlpha(0f);
        detailContainer.setVisibility(View.VISIBLE);

        detailContainer.animate()
                .alpha(1f)
                .setDuration(500)
                .setListener(null);
    }

    public void hideDetail() {

        detailContainer.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        detailContainer.setVisibility(View.GONE);
                    }
                });
    }








    private void bindUI(){

        TabLayout tabLayout = findViewById(R.id.TAB_Main);
        ViewPager viewPager = findViewById(R.id.VP_Main);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(movieCatalogContainerFragment,"Películas");
        tabAdapter.addFragment(tvShowCatalogContainerFragment,"Series");
        tabAdapter.addFragment(contentSearchFragment,"Todo");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(this, R.color.colorGray),
                ContextCompat.getColor(this, R.color.colorWhite));
        Utilities.hideKeyboardFromTab(tabLayout);

        detailContainer = findViewById(R.id.CTNR_Detail);
    }











    @Override
    public void onBackPressed() {
        if (detailContainer.getVisibility() == View.VISIBLE ) {
            hideDetail();
        } else {
            super.onBackPressed();
        }
    }

}
