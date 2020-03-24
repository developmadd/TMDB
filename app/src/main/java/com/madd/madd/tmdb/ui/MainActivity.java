package com.madd.madd.tmdb.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.madd.madd.tmdb.ui.ContentSearch.ContentSearchFragment;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.di.App;
import com.madd.madd.tmdb.utilities.TabAdapter;
import com.madd.madd.tmdb.utilities.Utilities;

public class MainActivity extends AppCompatActivity {



    private MovieCatalogContainerFragment movieCatalogContainerFragment = new MovieCatalogContainerFragment();
    private TVShowCatalogContainerFragment tvShowCatalogContainerFragment = new TVShowCatalogContainerFragment();
    private ContentSearchFragment contentSearchFragment = new ContentSearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App)getApplication()).getComponent().inject(this);
        bindUI();
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

    }











}
