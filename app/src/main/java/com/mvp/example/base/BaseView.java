package com.mvp.example.base;

import android.support.annotation.StringRes;

public interface BaseView {
    void showMessage(String message);

    void showMessage(@StringRes Integer stringResId);
}
