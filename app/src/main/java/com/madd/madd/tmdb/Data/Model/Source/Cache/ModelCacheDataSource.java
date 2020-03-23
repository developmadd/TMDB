package com.madd.madd.tmdb.Data.Model.Source.Cache;

import com.madd.madd.tmdb.Data.Model.Model.ModelList;
import com.madd.madd.tmdb.Data.Model.ModelDataSource;

import java.util.List;

public class ModelCacheDataSource  implements ModelDataSource.Cache {

    @Override
    public void getModel(String movieId, ModelDataSource.GetModel getModel) {

    }

    @Override
    public void getModelList(int page, ModelDataSource.GetList getList) {

    }

    @Override
    public void setModelList(List<ModelList.Model> modelList) {

    }
}
