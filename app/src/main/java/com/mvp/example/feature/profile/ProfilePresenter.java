package com.mvp.example.feature.profile;

import com.google.gson.Gson;
import com.mvp.example.dataSource.local.TableProfile;
import com.mvp.example.dataSource.remote.repository.Repository;
import com.mvp.example.dataSource.remote.repository.RepositoryCallback;
import com.mvp.example.model.profile.Profile;
import com.mvp.example.model.profile.Profiles;

public class ProfilePresenter {

    private ProfileView view;
    private Repository repository;
    private TableProfile tableProfile;

    ProfilePresenter(ProfileView view, Repository repository, TableProfile tableProfile) {
        this.view = view;
        this.repository = repository;
        this.tableProfile = tableProfile;
    }

    void requestProfile() {
        view.showLoading();
        repository.getProfile(new RepositoryCallback<Profiles>() {
            @Override
            public void onDataLoaded(Profiles data) {
                view.hideLoading();
                view.showProfileFromAPI(data.getProfile());
            }

            @Override
            public void onDataError(String error) {
                view.showMessage(error);
            }
        });
    }

    void saveProfile(Profile profile) {
        if (tableProfile.countByID(profile.getId()) > 0) {
            tableProfile.delete(profile.getId());
        }
        tableProfile.insert(profile);
    }

    void getProfileFromDB(int id) {
        String profile = tableProfile.selectWhereID(id);
        view.showProfileFromDB(new Gson().fromJson(profile, Profile.class));
    }
}
