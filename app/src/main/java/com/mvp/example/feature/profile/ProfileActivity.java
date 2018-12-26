package com.mvp.example.feature.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.mvp.example.R;
import com.mvp.example.base.BaseActivity;
import com.mvp.example.dataSource.local.TableProfile;
import com.mvp.example.feature.product.ProductActivity;
import com.mvp.example.model.profile.Profile;
import com.mvp.example.dataSource.remote.repository.Repository;

public class ProfileActivity extends BaseActivity implements ProfileView {

    @BindView(R.id.tv_remote)
    TextView tvRemote;
    @BindView(R.id.tv_local)
    TextView tvLocal;
    @BindView(R.id.btn_show_product)
    Button btnShowProduct;
    @BindView(R.id.lyt_progressbar)
    RelativeLayout lytProgressbar;

    private ProfilePresenter presenter;
    private Profile profile;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                if (profile != null) presenter.saveProfile(profile);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_show_product)
    void showProduct() {
        startActivity(new Intent(this, ProductActivity.class));
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
        this.profile = profile;
        tvRemote.setText(new Gson().toJson(profile));
        presenter.selectProfile(profile.getId());
    }

    @Override
    public void showProfileFromDB(String profile) {
        tvLocal.setText(profile);
    }

    @Override
    public void onSaveOrDelete(int id) {
        presenter.selectProfile(id);
    }
}
