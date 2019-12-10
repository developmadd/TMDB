package com.madd.madd.tmdb.DI;

import com.madd.madd.tmdb.Activities.MainActivity;
import com.madd.madd.tmdb.HTTP.TMDBModule;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogFragment;
import com.madd.madd.tmdb.MovieCatalog.MovieCatalogModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {  ApplicationModule.class,
                        MovieCatalogModule.class,
                        TMDBModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
    void inject(MovieCatalogFragment target);


}
