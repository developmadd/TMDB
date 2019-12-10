package com.madd.madd.tmdb.HTTP.Models;

import android.content.Context;

import com.madd.madd.tmdb.Services.TVShowService;
import com.madd.madd.tmdb.Utilities.References;
import com.madd.madd.tmdb.Utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

public class TVShow {

    private String id;
    private String title;
    private String posterPath;
    private String backdropPath;
    private String genre;
    private String year;
    private String overview;
    private String episodesNumber;
    private String seasonsNumber;
    private String voteAverage;

    public TVShow() {
    }

    private void parseJSON(JSONObject movieJSON) {
        try {

            this.id = String.valueOf(movieJSON.getInt("id"));
            this.title = movieJSON.getString("name");
            this.posterPath = "";
            if( !movieJSON.getString("poster_path").equals(References.NULL_ITEM) ) {
                this.posterPath = "https://image.tmdb.org/t/p/w500/" +
                        movieJSON.getString("poster_path");
            }
            this.backdropPath = "";
            if( !movieJSON.getString("backdrop_path").equals(References.NULL_ITEM) ) {
                this.backdropPath = "https://image.tmdb.org/t/p/w500/" +
                        movieJSON.getString("backdrop_path");
            }
            this.year = movieJSON.getString("first_air_date").substring(0,4);
            this.episodesNumber = String.valueOf(movieJSON.getInt("number_of_episodes"));
            this.seasonsNumber  = String.valueOf(movieJSON.getInt("number_of_seasons"));
            this.overview = movieJSON.getString("overview");
            this.voteAverage = String.valueOf(movieJSON.getDouble("vote_average"));

            StringBuilder genres = new StringBuilder();
            for ( int i = 0 ; i < movieJSON.getJSONArray("genres").length() ; i++ ){
                if ( i != 0 ){
                    genres.append(" - ");
                }
                genres.append(movieJSON.getJSONArray("genres").
                        getJSONObject(i).getString("name"));
            }
            this.genre = genres.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEpisodesNumber() {
        return episodesNumber;
    }

    public void setEpisodesNumber(String episodesNumber) {
        this.episodesNumber = episodesNumber;
    }

    public String getSeasonsNumber() {
        return seasonsNumber;
    }

    public void setSeasonsNumber(String seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }









    public void getFromAPI(Context context, String tvShowId, Utilities.GetMessage getMessage){
        TVShowService.getTVShow(context, tvShowId, (serverMessage, jsonTVShow) -> {
            if( serverMessage.equals(References.OK_MESSAGE) ){
                parseJSON(jsonTVShow);
            }
            getMessage.message(serverMessage);
        });
    }
}
