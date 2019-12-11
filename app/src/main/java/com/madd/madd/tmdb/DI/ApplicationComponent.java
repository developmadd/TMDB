package com.madd.madd.tmdb.DI;

import com.madd.madd.tmdb.Activities.MainActivity;
import com.madd.madd.tmdb.Fragments.ContentSearch.ContentSearchFragment;
import com.madd.madd.tmdb.Fragments.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.HTTP.TMDBModule;
import com.madd.madd.tmdb.Fragments.MovieCatalog.MovieCatalogFragment;
import com.madd.madd.tmdb.Fragments.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.Fragments.MovieDetail.MovieDetailFragment;
import com.madd.madd.tmdb.Fragments.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.Fragments.TVShowCatalog.TVShowCatalogFragment;
import com.madd.madd.tmdb.Fragments.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.Fragments.TVShowDetail.TVShowDetailFragment;
import com.madd.madd.tmdb.Fragments.TVShowDetail.TVShowDetailModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {  ApplicationModule.class,
                        MovieCatalogModule.class,
                        TVShowCatalogModule.class,
                        MovieDetailModule.class,
                        TVShowDetailModule.class,
                        ContentSearchModule.class,
                        TMDBModule.class })
public interface ApplicationComponent {

    void inject(MainActivity target);
    void inject(MovieCatalogFragment target);
    void inject(TVShowCatalogFragment target);
    void inject(MovieDetailFragment target);
    void inject(TVShowDetailFragment target);
    void inject(ContentSearchFragment target);
}
