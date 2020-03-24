package com.madd.madd.tmdb.data.entities;

import java.util.List;

public interface DataSource {

    interface GetList<T>{
        void onSuccess(List<T> list);
        void onError(String error);
    }

    interface GetEntity<T>{
        void onSuccess(T entity);
        void onError(String error);
    }

}
