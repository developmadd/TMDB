package com.madd.madd.tmdb.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.madd.madd.tmdb.Utilities.References;

import java.util.List;

public class ContentList_ {

    @SerializedName("page")
    @Expose
    int page;

    @SerializedName("total_pages")
    @Expose
    int totalPages;

    @SerializedName("results")
    @Expose
    List<Content> contentList;


    public static class Content {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("title")
        @Expose
        private String title;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPosterPath() {
            return "https://image.tmdb.org/t/p/w500/" + posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public int getContentType() {
            return title == null ? References.TV_TYPE : References.MOVIE_TYPE ;
        }


    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Content> getContentList() {
        return contentList;
    }

}
