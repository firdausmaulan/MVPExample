package com.mvp.example.dataSource.remote;

import com.mvp.example.model.profile.Profiles;
import com.mvp.example.model.product.Products;
import com.mvp.example.util.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    @GET("test/profile.php")
    Call<Profiles> getProfile();

    @GET("test/products.php")
    Call<Products> getProducts();

    class Builder {
        public ApiService build() {
            // Setup OKHTTP interceptor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            // Setup retrofit
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL);
            return retrofit.client(okHttpClient).build().create(ApiService.class);
        }
    }
}
