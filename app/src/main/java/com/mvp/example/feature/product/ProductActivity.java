package com.mvp.example.feature.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.mvp.example.R;
import com.mvp.example.base.BaseActivity;
import com.mvp.example.dataSource.remote.repository.Repository;
import com.mvp.example.feature.profile.ProfileActivity;
import com.mvp.example.model.product.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends BaseActivity implements ProductView {

    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.btn_show_profile)
    Button buttonShowProfile;

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

        productAdapter = new ProductAdapter();
        rvProduct.setAdapter(productAdapter);
    }

    @OnClick(R.id.btn_show_profile)
    void showProfile() {
        startActivity(new Intent(this, ProfileActivity.class));
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
        productAdapter.addList(listProduct);
    }
}
