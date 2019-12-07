package com.madd.madd.tmdb.Fragments.TVShow;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madd.madd.tmdb.Models.ContentList_;
import com.madd.madd.tmdb.Models.Lists.Content.ContentAdapter;
import com.madd.madd.tmdb.Models.Lists.Content.ContentList;
import com.madd.madd.tmdb.R;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowCatalog extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private TextView textViewEmpty;

    private ContentList tvShowList;

    private ContentList.OnContentSelected onTVShowSelected;
    public void setOnTVShowSelected(ContentList.OnContentSelected onTVShowSelected) {
        this.onTVShowSelected = onTVShowSelected;
    }

    private int listType;
    public void setListType(int listType) {
        this.listType = listType;
    }

    boolean searchBarAnimationStatus = false;   // Sin animación actualmente

    public TVShowCatalog() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tvshow_catalog, container, false);
        bindUI();
        getTVShowList();
        populateTVShowList();
        setEvents();

        return view;

    }



    private void getTVShowList(){
        tvShowList = new ContentList(getContext(), recyclerView, true,

                new ContentAdapter.ContentListener() {
                    @Override
                    public void onContentClick(ContentList_.Content selectedContent) {
                        onTVShowSelected.onContentClick(selectedContent);
                        Utilities.hideKeyboardFrom(searchView);
                    }

                    @Override
                    public void onSendMessage(boolean showMessage, String message) {
                        textViewEmpty.setVisibility(showMessage ? View.VISIBLE : View.GONE);
                        textViewEmpty.setText(message);
                    }

                    @Override
                    public void onRequestNextPage() {
                        /*
                            Solicitar siguiente pagina solo si no se esta
                            ejecutando una busqueda local
                        */
                        if (searchView.getQuery().toString().isEmpty()) {
                            populateTVShowList();
                        }
                    }
                });


    }


    public void populateTVShowList(){
        if( listType == References.POPULAR_TYPE){
            //tvShowList.getPopularTVShowList();
        } else if ( listType == References.ON_AIR_TYPE ){
            //tvShowList.getOnAirTVShowList();
        } else if ( listType == References.TOP_RATED_TYPE ){
            //tvShowList.getTopRatedTVShowList();
        }
    }





    private void setEvents(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //tvShowList.getCoincidenceMovie(s,false);
                return false;
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // No realizar animaciones mientras una se lleva a cabo
                if ( !searchBarAnimationStatus) {

                    int firstVisibleItem = ((LinearLayoutManager)
                            Objects.requireNonNull(recyclerView.getLayoutManager()))
                            .findFirstVisibleItemPosition();

                    if (dy > 0 && firstVisibleItem != 0) {
                        Utilities.animateTranslationY(searchView, -40, status -> {
                            searchBarAnimationStatus = status;
                        });
                        Utilities.animateTranslationY(recyclerView,0,null);
                    } else if (dy < 0) {
                        Utilities.animateTranslationY(searchView, 0, status -> {
                            searchBarAnimationStatus = status;
                        });
                        Utilities.animateTranslationY(recyclerView,40,null);
                    }

                }

            }
        });

    }





















    private void bindUI(){
        recyclerView = view.findViewById(R.id.CTNR_TVShow_Catalog);
        searchView = view.findViewById(R.id.SV_TVShow_Catalog);
        textViewEmpty = view.findViewById(R.id.TV_TVShow_Catalog_Empty);
    }







}
