package com.mvp.example.feature.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvp.example.R;
import com.mvp.example.base.BaseActivity;
import com.mvp.example.dataSource.local.TableProfile;
import com.mvp.example.dataSource.remote.repository.Repository;
import com.mvp.example.model.profile.Profile;
import com.mvp.example.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements ProfileView {

    @BindView(R.id.ivServer)
    CircleImageView ivServer;
    @BindView(R.id.tvNameServer)
    TextView tvNameServer;
    @BindView(R.id.tvPhoneServer)
    TextView tvPhoneServer;
    @BindView(R.id.tvAddressServer)
    TextView tvAddressServer;
    @BindView(R.id.ivLocal)
    CircleImageView ivLocal;
    @BindView(R.id.tvNameLocal)
    TextView tvNameLocal;
    @BindView(R.id.tvPhoneLocal)
    TextView tvPhoneLocal;
    @BindView(R.id.tvAddressLocal)
    TextView tvAddressLocal;
    @BindView(R.id.lyt_progressbar)
    RelativeLayout lytProgressbar;

    private ProfilePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        presenter = new ProfilePresenter(this,
                new Repository(),
                new TableProfile(this));

        presenter.requestProfile();
    }

    @Override
    public void showLoading() {
        lytProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        lytProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showProfileFromAPI(Profile profile) {
        ImageLoader.load(profile.getPhoto(), ivServer);
        tvNameServer.setText(profile.getName());
        tvPhoneServer.setText(profile.getPhone());
        tvAddressServer.setText(profile.getAddress());

        presenter.saveProfile(profile);
        presenter.getProfileFromDB(profile.getId());
    }

    @Override
    public void showProfileFromDB(Profile profile) {
        ImageLoader.load(profile.getPhoto(), ivLocal);
        tvNameLocal.setText(profile.getName());
        tvPhoneLocal.setText(profile.getPhone());
        tvAddressLocal.setText(profile.getAddress());
    }
}
