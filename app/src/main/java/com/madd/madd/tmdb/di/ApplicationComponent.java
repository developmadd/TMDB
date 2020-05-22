package com.madd.madd.tmdb.di;

import com.madd.madd.tmdb.data.entities.Cast.CastDataModule;
import com.madd.madd.tmdb.data.entities.ContentList.ContentListDataModule;
import com.madd.madd.tmdb.data.entities.Movie.MovieDataModule;
import com.madd.madd.tmdb.data.entities.TVShow.TVShowDataModule;
import com.madd.madd.tmdb.data.http.APIModule;
import com.madd.madd.tmdb.ui.MainActivity;
import com.madd.madd.tmdb.ui.MovieDetail.MovieDetailActivity;
import com.madd.madd.tmdb.ui.ContentSearch.ContentSearchFragment;
import com.madd.madd.tmdb.ui.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.ui.MovieCatalog.MovieCatalogFragment;
import com.madd.madd.tmdb.ui.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.ui.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.ui.TVShowCatalog.TVShowCatalogFragment;
import com.madd.madd.tmdb.ui.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.ui.TVShowDetail.TVShowDetailActivity;
import com.madd.madd.tmdb.ui.TVShowDetail.TVShowDetailModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {  ApplicationModule.class,
                        MovieCatalogModule.class,
                        TVShowCatalogModule.class,
                        MovieDetailModule.class,
                        TVShowDetailModule.class,
                        ContentSearchModule.class,

                        APIModule.class,
                        MovieDataModule.class,
                        CastDataModule.class,
                        TVShowDataModule.class,
                        ContentListDataModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
    void inject(MovieDetailActivity target);
    void inject(TVShowDetailActivity target);
    void inject(MovieCatalogFragment target);
    void inject(TVShowCatalogFragment target);
    void inject(ContentSearchFragment target);
}
