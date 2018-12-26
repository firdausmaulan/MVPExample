package com.mvp.example.feature.product;

import com.mvp.example.model.product.Products;
import com.mvp.example.dataSource.remote.repository.Repository;
import com.mvp.example.dataSource.remote.repository.RepositoryCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductPresenterTest {

    @Mock
    private ProductView view;

    @Mock
    private Repository repository;

    @Mock
    private Products response;

    @Captor
    private ArgumentCaptor<RepositoryCallback<Products>> argumentCaptor;

    private ProductPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ProductPresenter(view, repository);
    }

    @Test
    public void requestProductTest() {
        System.out.println("1");
        presenter.requestProduct();
        System.out.println("2");
        Mockito.verify(view).showLoading();
        System.out.println("3");
        Mockito.verify(repository).getProducts(argumentCaptor.capture());
        System.out.println("4");
        argumentCaptor.getValue().onDataLoaded(response);
        System.out.println("5");
        Mockito.verify(view).hideLoading();
        System.out.println("6");
        Mockito.verify(view).showListProduct(response.getProduct());
        System.out.println("7");
    }
}