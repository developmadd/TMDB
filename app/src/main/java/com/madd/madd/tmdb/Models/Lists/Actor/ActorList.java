package com.madd.madd.tmdb.Models.Lists.Actor;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.madd.madd.tmdb.Services.MovieService;
import com.madd.madd.tmdb.Services.TVShowService;
import com.madd.madd.tmdb.Utilities.References;

import java.util.ArrayList;
import java.util.List;

public class ActorList {

    private Context context;
    private RecyclerView recyclerView;

    private List<ActorCard> actorCardList = new ArrayList<>();
    private ActorAdapter actorAdapter;

    public ActorList(Context context, RecyclerView recyclerView) {
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

        MovieService.getMovieCast(context, movieId, (message, actorCardList) -> {
            if ( message.equals(References.OK_MESSAGE) ) {
                this.actorCardList.addAll(actorCardList);
                actorAdapter.notifyDataSetChanged();
            }
        });

    }

    public void getTVShowCast(String tvShowId){

        TVShowService.getTVShowCast(context, tvShowId, (message, actorCardList) -> {
            if ( message.equals(References.OK_MESSAGE) ) {
                this.actorCardList.addAll(actorCardList);
                actorAdapter.notifyDataSetChanged();
            }
        });

    }






    public interface GetActorList{
        void actorList(String message, List<ActorCard> actorCardList);
    }


}
