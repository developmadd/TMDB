package com.madd.madd.tmdb.Models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.madd.madd.tmdb.HTTP.Models.MovieList;
import com.madd.madd.tmdb.MovieCatalog.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class Movie_TVShowList {


    private Context context;
    private RecyclerView recyclerView;

    //private MovieAdapter.ContentListener contentListener;

    private List<MovieList.Movie> movieList_ = new ArrayList<>();
    int page = 1;
    private List<MovieList.Movie> filteredMovieList = new ArrayList<>();


    private MovieAdapter movieAdapter;
    private boolean singleContentType;          // false para series Y peliculas, true para series O peliculas


    public Movie_TVShowList(Context context, RecyclerView recyclerView,
                            boolean singleContentType
                            /*MovieAdapter.ContentListener contentListener*/) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.singleContentType = singleContentType;
        //this.contentListener = contentListener;
        //initList();
    }

/*
    private void initList(){
        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        movieAdapter = new MovieAdapter(
                filteredMovieList,
                singleContentType,
                contentListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }




















    // Criterios de llenado de lista
    public void getPopularMovieList(){

        MovieService.getMoviePopularList(page, new MovieService.GetContentList() {
            @Override
            public void onSuccess(MovieList movieList_) {
                page = movieList_.getPage();
                filteredMovieList.addAll(movieList_.getMovieList());
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                setErrorMessage(error);
            }
        });

    }

















    // Filtro Online


    /*private List<com.madd.madd.tmdb.Models.Movie_TVShowList.Movie> movieList = new ArrayList<>();
    private List<com.madd.madd.tmdb.Models.Movie_TVShowList.Movie> tvShowList = new ArrayList<>();

    public void initSearchByQuery(){
        this.filteredMovieList.clear();
        this.movieAdapter.notifyDataSetChanged();
        this.page = 1;
    }



    public void getContentListByQuery(String query){

        if ( !query.isEmpty() ) {
            final int[] counter = {0};
            MovieService.getMovieListByQuery(context, query, page, (serverMessage, contentList) -> {
                if (serverMessage.equals(References.OK_MESSAGE)) {
                    movieList = contentList;
                    showContentListByQuery(++counter[0] == 2);
                } else {
                    setErrorMessage(serverMessage);
                }

            });

            TVShowService.getTVShowListByQuery(context, query, page, (serverMessage, contentList) -> {
                if (serverMessage.equals(References.OK_MESSAGE)) {
                    tvShowList = contentList;
                    showContentListByQuery(++counter[0] == 2);
                } else{
                    setErrorMessage(serverMessage);
                }


            });
        } else {
            initSearchByQuery();
            contentListener.onSendMessage(true,
                    "Introduzca nombre de película o serie " );
        }

    }



    private void showContentListByQuery( boolean listsReady ){
        if ( listsReady ) {                                                // Ambas listas han sido cargadas
            if (!movieList.isEmpty() || !tvShowList.isEmpty()) {            // Al menos una lista trajo nuevos elementos
                filteredMovieList.addAll(movieList);
                filteredMovieList.addAll(tvShowList);

                movieAdapter.notifyDataSetChanged();
                page++;
            }
            contentListener.onSendMessage(filteredMovieList.isEmpty(),
                    "Sin resultados");
        }
    }















    // Filtro Offline

    public void getCoincidenceMovie(String coincidenceText, boolean paginate){

        filteredMovieList.clear();

        if (!coincidenceText.isEmpty()) {
            String compare = Utilities.cleanString(coincidenceText);
            for (com.madd.madd.tmdb.Models.Movie_TVShowList.Movie content : contentList) {

                String original = Utilities.cleanString(content.getTitle());
                if (original.startsWith(compare)) {
                    filteredMovieList.add(content);
                }
            }
            for (com.madd.madd.tmdb.Models.Movie_TVShowList.Movie content : contentList) {

                String original = Utilities.cleanString(content.getTitle());
                if ( !original.startsWith(compare) && original.contains(compare)  ) {
                    filteredMovieList.add(content);
                }
            }
            movieAdapter.notifyDataSetChanged();
        } else {
            filteredMovieList.addAll(contentList);
            if ( paginate ) {
                movieAdapter.notifyItemRangeInserted(
                        (page - 1) * References.MOVIE_PAGINATE_STEP,
                        References.MOVIE_PAGINATE_STEP);
            } else {
                movieAdapter.notifyDataSetChanged();
            }
        }

        contentListener.onSendMessage(filteredMovieList.isEmpty(),"Sin resultados");

    }*/










    private void setErrorMessage(String serverMessage){
        /*if (serverMessage.equals(References.NOT_INTERNET_ERROR)) {
            contentListener.onSendMessage(true,
                    "Parece que no tienes una conexión a internet, " +
                            "asegurate de conectarte a una red y vuelve a intentarlo");
        } else {
            contentListener.onSendMessage(true,
                    "Ha ocurrido un error, intente reiniciar la aplicación porfavor");
        }*/
    }











}
