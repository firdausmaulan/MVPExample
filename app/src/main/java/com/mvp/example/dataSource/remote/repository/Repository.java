package com.mvp.example.dataSource.remote.repository;

import com.mvp.example.model.product.Products;
import com.mvp.example.model.profile.Profiles;
import com.mvp.example.dataSource.remote.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiService service = new ApiService.Builder().build();

    public void getProfile(final RepositoryCallback callback) {
        service.getProfile().enqueue(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                if (response.isSuccessful()) {
                    callback.onDataLoaded(response.body());
                } else {
                    callback.onDataError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                callback.onDataError(t.getMessage());
            }
        });
    }

    public void getProducts(final RepositoryCallback callback) {
        service.getProducts().enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    callback.onDataLoaded(response.body());
                } else {
                    callback.onDataError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                callback.onDataError(t.getMessage());
            }
        });
    }

}
