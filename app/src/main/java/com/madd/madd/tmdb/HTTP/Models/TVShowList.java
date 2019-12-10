package com.madd.madd.tmdb.HTTP.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.madd.madd.tmdb.Utilities.References;

import java.util.List;

public class TVShowList {

    @SerializedName("page")
    @Expose
    int page;

    @SerializedName("results")
    @Expose
    List<TVShow> tvShowList;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TVShow> getTvShowList() {
        return tvShowList;
    }

    public static class TVShow {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("poster_path")
        @Expose
        private String posterPath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getPosterPath() {
            return "https://image.tmdb.org/t/p/w500/" + posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }



    }

}
