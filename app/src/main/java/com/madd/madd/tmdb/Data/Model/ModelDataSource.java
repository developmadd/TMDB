package com.madd.madd.tmdb.Data.Model;

import com.madd.madd.tmdb.Data.Model.Model.ModelList;
import com.madd.madd.tmdb.Data.Movie.Model.Movie;
import com.madd.madd.tmdb.Data.Movie.Model.MovieList;
import com.madd.madd.tmdb.Data.Movie.MovieDataSource;

import java.util.List;

public interface ModelDataSource {

    interface Repository{
        void getModel(String movieId, GetModel getModel);
        void getModelList(int page, GetList getList);
    }
    interface Remote{
        void getModel(String movieId, GetModel getModel);
        void getModelList(int page, GetList getList);
    }
    interface Cache{
        void getModel(String movieId, GetModel getModel);
        void getModelList(int page, GetList getList);
        void setModelList(List<ModelList.Model> modelList);
    }

    interface GetList{
        void onSuccess(MovieList movieList);
        void onError(String error);
    }

    interface GetModel{
        void onSuccess(Object model);
        void onError(String error);
    }

}
