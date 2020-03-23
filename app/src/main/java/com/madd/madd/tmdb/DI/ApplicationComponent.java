package com.madd.madd.tmdb.DI;

import com.madd.madd.tmdb.Data.Cast.CastDataModule;
import com.madd.madd.tmdb.Data.ContentList.ContentListDataModule;
import com.madd.madd.tmdb.Data.Movie.MovieDataModule;
import com.madd.madd.tmdb.Data.TVShow.TVShowDataModule;
import com.madd.madd.tmdb.UI.MainActivity;
import com.madd.madd.tmdb.UI.MovieDetail.MovieDetailActivity;
import com.madd.madd.tmdb.UI.ContentSearch.ContentSearchFragment;
import com.madd.madd.tmdb.UI.ContentSearch.ContentSearchModule;
import com.madd.madd.tmdb.Data.HTTP.HTTPModule;
import com.madd.madd.tmdb.UI.MovieCatalog.MovieCatalogFragment;
import com.madd.madd.tmdb.UI.MovieCatalog.MovieCatalogModule;
import com.madd.madd.tmdb.UI.MovieDetail.MovieDetailModule;
import com.madd.madd.tmdb.UI.TVShowCatalog.TVShowCatalogFragment;
import com.madd.madd.tmdb.UI.TVShowCatalog.TVShowCatalogModule;
import com.madd.madd.tmdb.UI.TVShowDetail.TVShowDetailActivity;
import com.madd.madd.tmdb.UI.TVShowDetail.TVShowDetailModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {  ApplicationModule.class,
                        MovieCatalogModule.class,
                        TVShowCatalogModule.class,
                        MovieDetailModule.class,
                        TVShowDetailModule.class,
                        ContentSearchModule.class,

                        HTTPModule.class,
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
