package com.madd.madd.tmdb.DI;

import com.madd.madd.tmdb.Activities.MainActivity;
import com.madd.madd.tmdb.ContentSearch.ContentSearchFragment;
import com.madd.madd.tmdb.ContentSearch.ContentSearchModel;
import com.madd.madd.tmdb.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.HTTP.TMDBModule;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogContainer;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogFragment;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.MovieDetail.MovieDetailFragment;
import com.madd.madd.tmdb.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.TVShowCatalog.TVShowCatalogContainer;
import com.madd.madd.tmdb.TVShowCatalog.TVShowCatalogFragment;
import com.madd.madd.tmdb.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.TVShowDetail.TVShowDetailFragment;
import com.madd.madd.tmdb.TVShowDetail.TVShowDetailModule;

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
