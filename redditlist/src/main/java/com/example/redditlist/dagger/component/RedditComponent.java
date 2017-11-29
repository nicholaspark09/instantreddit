package com.example.redditlist.dagger.component;

import android.content.Context;

import com.example.redditlist.MainActivity;
import com.example.redditlist.dagger.module.RedditListModule;
import com.example.redditlist.ui.RedditListFragment;
import com.example.vn008xw.myapplication.dagger.module.AppModule;
import com.example.vn008xw.myapplication.dagger.module.NetworkModule;
import com.example.vn008xw.myapplication.dagger.module.RedditModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                RedditModule.class,
                RedditListModule.class,
                AndroidSupportInjectionModule.class
        })
public interface RedditComponent {

    void inject(MainActivity mainActivity);

    void inject(RedditListFragment fragment);

    @Component.Builder
    interface Builder {

        RedditComponent build();

        @BindsInstance
        Builder bindContext(Context context);
    }
}