package com.example.redditlist.dagger.module;

import com.example.redditlist.ui.RedditListContract;
import com.example.redditlist.ui.RedditListPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RedditListModule {

    @Binds
    public abstract RedditListContract.Presenter providePresenter(RedditListPresenter presenter);
}