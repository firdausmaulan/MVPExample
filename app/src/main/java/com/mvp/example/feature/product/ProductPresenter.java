package com.mvp.example.feature.product;

import com.mvp.example.model.product.Products;

import com.mvp.example.dataSource.remote.repository.Repository;
import com.mvp.example.dataSource.remote.repository.RepositoryCallback;

public class ProductPresenter {

    private ProductView view;
    private Repository repository;

    ProductPresenter(ProductView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    void requestProduct() {
        view.showLoading();
        repository.getProducts(new RepositoryCallback<Products>() {
            @Override
            public void onDataLoaded(Products data) {
                view.hideLoading();
                view.showListProduct(data.getProducts());
            }

            @Override
            public void onDataError(String error) {
                view.hideLoading();
                view.showMessage(error);
            }
        });
    }

}
