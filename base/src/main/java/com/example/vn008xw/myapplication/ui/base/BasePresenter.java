package com.example.vn008xw.myapplication.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends BaseView> implements BasePresenterContract<T> {

    @NonNull
    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Nullable
    private T mView;

    public T getView() {
        return mView;
    }

    @Override
    public void attachView(@NonNull T view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}