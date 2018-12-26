package com.mvp.example.feature.profile;

import com.mvp.example.dataSource.local.TableProfile;
import com.mvp.example.model.profile.Profiles;
import com.mvp.example.dataSource.remote.repository.Repository;
import com.mvp.example.dataSource.remote.repository.RepositoryCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePresenterTest {

    @Mock
    private ProfileView view;

    @Mock
    private TableProfile tableProfile;

    @Mock
    private Repository repository;

    @Mock
    private Profiles response;

    @Captor
    private ArgumentCaptor<RepositoryCallback<Profiles>> argumentCaptor;

    private ProfilePresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ProfilePresenter(view, repository, tableProfile);
    }

    @Test
    public void requestProfileTest() {
        System.out.println("1");
        presenter.requestProfile();
        System.out.println("2");
        Mockito.verify(view).showLoading();
        System.out.println("3");
        Mockito.verify(repository).getProfile(argumentCaptor.capture());
        System.out.println("4");
        argumentCaptor.getValue().onDataLoaded(response);
        System.out.println("5");
        Mockito.verify(view).hideLoading();
        System.out.println("6");
        Mockito.verify(view).showProfileFromAPI(response.getProfile());
        System.out.println("7");
    }
}