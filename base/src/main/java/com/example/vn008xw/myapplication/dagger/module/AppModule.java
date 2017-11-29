package com.example.vn008xw.myapplication.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    @Named("IoThread")
    Scheduler provideIoThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("MainThread")
    Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }
}