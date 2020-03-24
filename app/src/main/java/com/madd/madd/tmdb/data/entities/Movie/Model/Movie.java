package com.madd.madd.tmdb.data.entities.Movie.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movie {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("release_date")
    @Expose
    private String year;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("vote_average")
    @Expose
    private String voteAverage;

    private long tampStamp;


    public Movie() {

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
        return posterPath != null && !posterPath.isEmpty() ? "https://image.tmdb.org/t/p/w500/" + posterPath : "";
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath != null && !backdropPath.isEmpty() ? "https://image.tmdb.org/t/p/w500/" + backdropPath : "";
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getGenre() {
        return genres != null && !genres.isEmpty() ? genres.get(0).getName() : "";
    }

    public String getYear() {
        return year.substring(0,4);
    }

    public void setYear(String year) {
        this.year = year;
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


    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
