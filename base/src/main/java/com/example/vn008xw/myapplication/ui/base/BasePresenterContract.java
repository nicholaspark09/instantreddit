package com.example.vn008xw.myapplication.ui.base;

import android.support.annotation.NonNull;

public interface BasePresenterContract<T extends BaseView> {

    void attachView(@NonNull T view);

    void removeView();
}