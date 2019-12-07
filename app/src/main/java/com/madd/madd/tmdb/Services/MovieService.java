package com.madd.madd.tmdb.Services;

import android.content.Context;

import com.madd.madd.tmdb.Models.ContentList_;
import com.madd.madd.tmdb.Models.Lists.Actor.ActorList;
import com.madd.madd.tmdb.Models.Lists.Content.ContentList;
import com.madd.madd.tmdb.Models.Lists.Actor.ActorCard;
import com.madd.madd.tmdb.Models.Movie;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Retrofit.API;
import com.madd.madd.tmdb.Utilities.Retrofit.TMDBService;
import com.madd.madd.tmdb.Utilities.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieService {



    public interface GetMovie{
        void onSuccess(Movie movie);
        void onError(String error);
    }
    public interface GetContentList{
        void onSuccess(ContentList_ movieList);
        void onError(String error);
    }


    // Read

    public static void getMovie(String movieId,
                                GetMovie getMovie) {


        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<Movie> movieCall = service.getMovie(movieId,References.TMDB_API_KEY, References.TMDB_LANGUAGE);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                getMovie.onSuccess(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                getMovie.onError(t.getLocalizedMessage());
            }
        });

    }




    // Lists

    public static void getMovieCast(Context context, String movieId,
                                    ActorList.GetActorList getActorList ){

        String url = "https://api.themoviedb.org/3/movie/" + movieId +"/credits?api_key=" +
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


    public static void getMoviePopularList(int page,
                                           GetContentList getMovieList){

        TMDBService service = API.getAPI().create(TMDBService.class);

        Call<ContentList_> movieList = service.getMoviePopularList(References.TMDB_API_KEY,References.TMDB_LANGUAGE,page);

        movieList.enqueue(new Callback<ContentList_>() {
            @Override
            public void onResponse(Call<ContentList_> call, Response<ContentList_> response) {
                getMovieList.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ContentList_> call, Throwable t) {
                getMovieList.onError(t.getLocalizedMessage());
            }


        });

    }




}

