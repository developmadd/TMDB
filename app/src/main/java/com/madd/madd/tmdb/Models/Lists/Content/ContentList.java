package com.madd.madd.tmdb.Models.Lists.Content;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.madd.madd.tmdb.Models.ContentList_;
import com.madd.madd.tmdb.Services.MovieService;
import com.madd.madd.tmdb.Services.TVShowService;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class ContentList {


    private Context context;
    private RecyclerView recyclerView;

    private ContentAdapter.ContentListener contentListener;

    private List<ContentList_.Content> contentList_ = new ArrayList<>();
    int page = 1;
    private List<ContentList_.Content> filteredContentList = new ArrayList<>();


    private ContentAdapter contentAdapter;
    private boolean singleContentType;          // false para series Y peliculas, true para series O peliculas


    public ContentList(Context context, RecyclerView recyclerView,
                       boolean singleContentType,
                       ContentAdapter.ContentListener contentListener) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.singleContentType = singleContentType;
        this.contentListener = contentListener;
        initList();
    }


    private void initList(){
        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        contentAdapter = new ContentAdapter(
                filteredContentList,
                singleContentType,
                contentListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contentAdapter);
    }




















    // Criterios de llenado de lista
    public void getPopularMovieList(){

        MovieService.getMoviePopularList(page, new MovieService.GetContentList() {
            @Override
            public void onSuccess(ContentList_ movieList_) {
                page = movieList_.getPage();
                filteredContentList.addAll(movieList_.getContentList());
                contentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                setErrorMessage(error);
            }
        });

    }

















    // Filtro Online


    /*private List<com.madd.madd.tmdb.Models.ContentList.Content> movieList = new ArrayList<>();
    private List<com.madd.madd.tmdb.Models.ContentList.Content> tvShowList = new ArrayList<>();

    public void initSearchByQuery(){
        this.filteredContentList.clear();
        this.contentAdapter.notifyDataSetChanged();
        this.page = 1;
    }



    public void getContentListByQuery(String query){

        if ( !query.isEmpty() ) {
            final int[] counter = {0};
            MovieService.getMovieListByQuery(context, query, page, (serverMessage, contentList) -> {
                if (serverMessage.equals(References.OK_MESSAGE)) {
                    movieList = contentList;
                    showContentListByQuery(++counter[0] == 2);
                } else{
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
                filteredContentList.addAll(movieList);
                filteredContentList.addAll(tvShowList);

                contentAdapter.notifyDataSetChanged();
                page++;
            }
            contentListener.onSendMessage(filteredContentList.isEmpty(),
                    "Sin resultados");
        }
    }















    // Filtro Offline

    public void getCoincidenceMovie(String coincidenceText, boolean paginate){

        filteredContentList.clear();

        if (!coincidenceText.isEmpty()) {
            String compare = Utilities.cleanString(coincidenceText);
            for (com.madd.madd.tmdb.Models.ContentList.Content content : contentList) {

                String original = Utilities.cleanString(content.getTitle());
                if (original.startsWith(compare)) {
                    filteredContentList.add(content);
                }
            }
            for (com.madd.madd.tmdb.Models.ContentList.Content content : contentList) {

                String original = Utilities.cleanString(content.getTitle());
                if ( !original.startsWith(compare) && original.contains(compare)  ) {
                    filteredContentList.add(content);
                }
            }
            contentAdapter.notifyDataSetChanged();
        } else {
            filteredContentList.addAll(contentList);
            if ( paginate ) {
                contentAdapter.notifyItemRangeInserted(
                        (page - 1) * References.MOVIE_PAGINATE_STEP,
                        References.MOVIE_PAGINATE_STEP);
            } else {
                contentAdapter.notifyDataSetChanged();
            }
        }

        contentListener.onSendMessage(filteredContentList.isEmpty(),"Sin resultados");

    }*/










    private void setErrorMessage(String serverMessage){
        if (serverMessage.equals(References.NOT_INTERNET_ERROR)) {
            contentListener.onSendMessage(true,
                    "Parece que no tienes una conexión a internet, " +
                            "asegurate de conectarte a una red y vuelve a intentarlo");
        } else {
            contentListener.onSendMessage(true,
                    "Ha ocurrido un error, intente reiniciar la aplicación porfavor");
        }
    }








    public interface OnContentSelected {
        void onContentClick(ContentList_.Content content);
    }

    public interface GetContentList {
        void contentList(String message, List<ContentList_.Content> contentList);
    }


}
