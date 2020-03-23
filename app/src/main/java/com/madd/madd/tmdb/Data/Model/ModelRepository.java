package com.madd.madd.tmdb.Data.Model;

public class ModelRepository implements ModelDataSource.Repository{

    private ModelDataSource.Remote remote;
    private ModelDataSource.Cache cache;

    public ModelRepository(ModelDataSource.Remote remote, ModelDataSource.Cache cache) {
        this.remote = remote;
        this.cache = cache;
    }

    @Override
    public void getModel(String movieId, ModelDataSource.GetModel getModel) {

    }

    @Override
    public void getModelList(int page, ModelDataSource.GetList getList) {

    }
}
