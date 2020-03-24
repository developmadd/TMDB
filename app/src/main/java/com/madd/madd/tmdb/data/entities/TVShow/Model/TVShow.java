package com.madd.madd.tmdb.data.entities.TVShow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShow {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("first_air_date")
    @Expose
    private String year;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("number_of_episodes")
    @Expose
    private String episodesNumber;

    @SerializedName("number_of_seasons")
    @Expose
    private String seasonsNumber;

    @SerializedName("vote_average")
    @Expose
    private String voteAverage;

    private long tampStamp;


    public TVShow() {

    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genres.get(0).getName();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500/" + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w500/" + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getYear() {
        return year.substring(0,4);
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

    public long getTampStamp() {
        return tampStamp;
    }

    public void setTampStamp() {
        this.tampStamp = System.currentTimeMillis();
    }

    public class Genre {

        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }




}
