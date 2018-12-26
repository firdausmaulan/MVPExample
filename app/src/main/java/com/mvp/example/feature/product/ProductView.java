package com.mvp.example.feature.product;

import com.mvp.example.base.BaseView;
import com.mvp.example.model.product.Product;

import java.util.List;

interface ProductView extends BaseView {
    void showLoading();

    void hideLoading();

    void showListProduct(List<Product> listProduct);
}