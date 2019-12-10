package com.madd.madd.tmdb.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.madd.madd.tmdb.Fragments.GeneralSearch;
import com.madd.madd.tmdb.Fragments.Movie.MovieDetail;
import com.madd.madd.tmdb.Fragments.Movie.MovieSearch;
import com.madd.madd.tmdb.Fragments.TVShow.TVShowDetail;
import com.madd.madd.tmdb.Fragments.TVShow.TVShowSearch;
import com.madd.madd.tmdb.Models.MovieList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Root.App;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.TabAdapter;
import com.madd.madd.tmdb.Utilities.Utilities;

public class MainActivity extends AppCompatActivity {


    private FrameLayout detailContainer;

    private MovieSearch movieSearch = new MovieSearch();
    private TVShowSearch tvShowSearch = new TVShowSearch();
    private GeneralSearch generalSearch = new GeneralSearch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App)getApplication()).getComponent().inject(this);
        bindUI();
        setEvents();
    }









    private void setEvents(){

        movieSearch.setOnMovieSelected(this::showMovieDetail);

        tvShowSearch.setOnTVShowSelected(this::showTVShowDetail);

        generalSearch.setOnContentSelected(contentCard -> {
            if( contentCard.getContentType() == References.MOVIE_TYPE ){
                showMovieDetail(contentCard);
            } else if ( contentCard.getContentType() == References.TV_TYPE ){
                showTVShowDetail(contentCard);
            }
        });

    }

    private void showTVShowDetail(MovieList.Movie tvShow){
        TVShowDetail tvShowDetail = new TVShowDetail();
        tvShowDetail.setTVShowId(tvShow.getId());
        tvShowDetail.setOnTvShowDetailClose(this::hideDetail);

        getSupportFragmentManager().beginTransaction().replace(R.id.CTNR_Detail, tvShowDetail)
                .commit();
        showDetail();

    }
    private void showMovieDetail(MovieList.Movie movie){
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.setMovieId(movie.getId());
        movieDetail.setOnMovieDetailClose(this::hideDetail);

        getSupportFragmentManager().beginTransaction().replace(R.id.CTNR_Detail, movieDetail)
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
        tabAdapter.addFragment(movieSearch,"Películas");
        tabAdapter.addFragment(tvShowSearch,"Series");
        tabAdapter.addFragment(generalSearch,"Todo");

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
