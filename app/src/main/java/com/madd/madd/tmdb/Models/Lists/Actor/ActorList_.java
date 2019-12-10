package com.madd.madd.tmdb.Models.Lists.Actor;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.madd.madd.tmdb.HTTP.Models.Cast;
import com.madd.madd.tmdb.Services.MovieService;

import java.util.ArrayList;
import java.util.List;

public class ActorList_ {

    private Context context;
    private RecyclerView recyclerView;

    private List<Cast.Actor> actorCardList = new ArrayList<>();
    private ActorAdapter actorAdapter;

    public ActorList_(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        initList();
    }

    private void initList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        actorAdapter = new ActorAdapter( actorCardList );

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(actorAdapter);
    }







    // Criterios de llenado de lista
    public void getMovieCast(String movieId){

        MovieService.getMovieCast(movieId, new MovieService.GetCast() {
            @Override
            public void onSuccess(Cast cast) {
                actorCardList.addAll(cast.getActorList());
                actorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Log.e("cast",error);
            }
        });

    }

    public void getTVShowCast(String tvShowId){

        /*TVShowService.getTVShowCast(context, tvShowId, (message, actorCardList) -> {
            if ( message.equals(References.OK_MESSAGE) ) {
                this.actorCardList.addAll(actorCardList);
                actorAdapter.notifyDataSetChanged();
            }
        });*/

    }







}
