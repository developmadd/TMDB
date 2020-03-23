package com.madd.madd.tmdb.Data.TVShow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowList {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private List<TVShow> tvShowList;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TVShow> getTvShowList() {
        return tvShowList;
    }


    public TVShowList(int page, List<TVShow> tvShowList) {
        this.page = page;
        this.tvShowList = tvShowList;
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

        public TVShow() {

        }

        public TVShow(String id, String name, String posterPath) {
            this.id = id;
            this.name = name;
            this.posterPath = posterPath;
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

        public String getPosterPath() {
            return "https://image.tmdb.org/t/p/w500/" + posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }



    }

}
