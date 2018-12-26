package com.mvp.example.dataSource.remote.repository;

public interface RepositoryCallback<T> {
    void onDataLoaded(T data);

    void onDataError(String error);
}