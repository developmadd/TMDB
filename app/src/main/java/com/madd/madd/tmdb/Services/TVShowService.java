package com.madd.madd.tmdb.Services;

import android.content.Context;


import com.madd.madd.tmdb.Models.Lists.Actor.ActorList_;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Utilities;
import com.madd.madd.tmdb.Utilities.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class TVShowService {




    // Read

    /*public static void getTVShow(Context context, String tvShowId,
                                 Utilities.GetJSONObject getJSONObject) {
        String url = "https://api.themoviedb.org/3/tv/" + tvShowId +"?api_key="+
                References.TMDB_API_KEY +"&language=es-MX";
        VolleyService.getInstance(context).getData(url, true,
                new VolleyService.GetVolleyResponse() {
                    @Override
                    public void notifySuccess(JSONObject response) {
                        getJSONObject.jsonObject(References.OK_MESSAGE, response);
                    }

                    @Override
                    public void notifyError(String error) {
                        getJSONObject.jsonObject(error, null);
                    }
                });

    }






    // Lists

    public static void getTVShowCast(Context context, String tvShowId,
                                     ActorList_.GetActorList getActorList){

        String url = "https://api.themoviedb.org/3/tv/" + tvShowId +"/credits?api_key=" +
                References.TMDB_API_KEY;

        VolleyService.getInstance(context).getData(url, true,
                new VolleyService.GetVolleyResponse() {
                    @Override
                    public void notifySuccess(JSONObject response) {
                        try {
                            List<ActorCard> actorCardList = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("cast");
                            for (int i = 0; i < jsonArray.length() && i < References.CAST_LIMIT; i++ ){
                                ActorCard actorCard = new ActorCard(jsonArray.getJSONObject(i));
                                actorCardList.add(actorCard);
                            }
                            getActorList.actorList(References.OK_MESSAGE,actorCardList);
                        } catch (JSONException error) {
                            getActorList.actorList(error.getMessage(),null);
                        }
                    }

                    @Override
                    public void notifyError(String error) {
                        getActorList.actorList(error,null);
                    }
                });
    }
*/






}

