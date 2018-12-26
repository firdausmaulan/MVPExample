package com.mvp.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showMessage(String message) {
        View rootView = getWindow().getDecorView().getRootView();
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(Integer stringResId) {
        View rootView = getWindow().getDecorView().getRootView();
        Snackbar.make(rootView, getString(stringResId), Snackbar.LENGTH_SHORT).show();
    }
}
