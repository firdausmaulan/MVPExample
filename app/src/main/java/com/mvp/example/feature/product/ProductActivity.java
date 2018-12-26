package com.mvp.example.feature.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvp.example.R;
import com.mvp.example.base.BaseActivity;
import com.mvp.example.model.product.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.mvp.example.dataSource.remote.repository.Repository;

public class ProductActivity extends BaseActivity implements ProductView {

    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private ProductPresenter presenter;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        presenter = new ProductPresenter(this, new Repository());
        presenter.requestProduct();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.requestProduct();
            }
        });
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showListProduct(List<Product> listProduct) {
        productAdapter = new ProductAdapter(listProduct);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        rvProduct.setAdapter(productAdapter);
    }
}
