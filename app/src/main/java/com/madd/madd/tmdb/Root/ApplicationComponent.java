package com.madd.madd.tmdb.Root;

import com.madd.madd.tmdb.Activities.MainActivity;
import com.madd.madd.tmdb.PopularMovieList.MovieCatalogFragment;
import com.madd.madd.tmdb.PopularMovieList.MovieCatalogModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class,
                        MovieCatalogModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
    void inject(MovieCatalogFragment target);


}
