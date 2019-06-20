package com.mvp.example.feature.profile;

import com.mvp.example.base.BaseView;
import com.mvp.example.model.profile.Profile;

interface ProfileView extends BaseView {
    void showLoading();

    void hideLoading();

    void showProfileFromAPI(Profile profile);

    void showProfileFromDB(Profile profile);
}