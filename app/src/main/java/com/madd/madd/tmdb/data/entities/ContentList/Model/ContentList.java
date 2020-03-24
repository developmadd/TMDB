package com.madd.madd.tmdb.data.entities.ContentList.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentList {

    @SerializedName("page")
    @Expose
    int page;

    @SerializedName("results")
    @Expose
    List<Content> contentList;


    public ContentList(int page, List<Content> contentList) {
        this.page = page;
        this.contentList = contentList;
    }

    public ContentList() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public static class Content {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("poster_path")
        @Expose
        private String posterPath;


        public Content(String id, String name, String title, String posterPath) {
            this.id = id;
            this.name = name;
            this.title = title;
            this.posterPath = posterPath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name == null ? title : name;
        }

        public String getPosterPath() {
            return "https://image.tmdb.org/t/p/w500/" + posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public boolean isMovie(){
            return name == null;
        }

    }

}
