package com.madd.madd.tmdb.Data.Model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelList {

    @SerializedName("page")
    @Expose
    int page;

    @SerializedName("results")
    @Expose
    List<Model> modelList;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Model> getMovieList() {
        return modelList;
    }


    public ModelList(int page, List<Model> modelList) {
        this.page = page;
        this.modelList = modelList;
    }

    public static class Model {

        @SerializedName("id")
        @Expose
        private String id;

        public Model() {
        }
    }

}
