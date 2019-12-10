package com.madd.madd.tmdb.PopularMovieList;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieCatalogModule {

    @Provides
    public MovieCatalogContract.Presenter providePopularMovieListPresenter(MovieCatalogContract.Model model){
        return new MovieCatalogPresenter(model);
    }

    @Provides
    public MovieCatalogContract.Model providePopularMovieListModel(){
        return new MovieCatalogModel();
    }
}
