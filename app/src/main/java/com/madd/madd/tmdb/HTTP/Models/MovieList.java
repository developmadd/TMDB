package com.madd.madd.tmdb.HTTP.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("page")
    @Expose
    int page;

    @SerializedName("results")
    @Expose
    List<Movie> movieList;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }


    public MovieList(int page, List<Movie> movieList) {
        this.page = page;
        this.movieList = movieList;
    }

    public static class Movie {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("poster_path")
        @Expose
        private String posterPath;


        public Movie() { }

        public Movie(String id, String title, String posterPath) {
            this.id = id;
            this.title = title;
            this.posterPath = posterPath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title ;
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

    }


}
