package com.madd.madd.tmdb.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.madd.madd.tmdb.Utilities.Utilities;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralSearch extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private TextView textViewEmpty;

    private ContentList contentList;

    private ContentList.OnContentSelected onContentSelected;
    public void setOnContentSelected(ContentList.OnContentSelected onContentSelected) {
        this.onContentSelected = onContentSelected;
    }


    boolean searchBarAnimationStatus = false;   // Sin animación actualmente

    public GeneralSearch() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_general_search, container, false);
        bindUI();
        getMovieList();
        setEvents();

        return view;

    }



    private void getMovieList(){
        contentList = new ContentList(getContext(), recyclerView, false,

                new ContentAdapter.ContentListener() {
                    @Override
                    public void onContentClick(ContentList_.Content selectedMovieCard) {
                        onContentSelected.onContentClick(selectedMovieCard);
                        Utilities.hideKeyboardFrom(searchView);
                    }

                    @Override
                    public void onSendMessage(boolean showMessage, String message) {
                        textViewEmpty.setVisibility(showMessage ? View.VISIBLE : View.GONE);
                        textViewEmpty.setText(message);
                    }


                    @Override
                    public void onRequestNextPage() {
                        populateMovieList(searchView.getQuery().toString());
                    }
                });

        textViewEmpty.setVisibility(View.VISIBLE );
        textViewEmpty.setText("Introduzca nombre de película o serie ");

    }


    public void populateMovieList(String query){
        //contentList.getContentListByQuery(query);
    }




    private CountDownTimer countDownTimer;
    private void setEvents(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                countDownTimer = new CountDownTimer(400, 100) {
                    public void onTick(long millisUntilFinished) {}
                    public void onFinish() {

                        //contentList.initSearchByQuery();
                        populateMovieList(s);

                    }
                }.start();

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
        recyclerView = view.findViewById(R.id.CTNR_General_Search);
        searchView = view.findViewById(R.id.SV_General_Search);
        textViewEmpty = view.findViewById(R.id.TV_General_Search_Empty);
    }







}
