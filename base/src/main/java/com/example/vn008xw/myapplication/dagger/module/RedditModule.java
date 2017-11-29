package com.example.vn008xw.myapplication.dagger.module;

import com.example.vn008xw.myapplication.dagger.Remote;
import com.example.vn008xw.myapplication.data.api.RedditService;
import com.example.vn008xw.myapplication.data.reddit.RedditDataSource;
import com.example.vn008xw.myapplication.data.reddit.RedditRemoteSource;
import com.example.vn008xw.myapplication.data.reddit.RedditRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RedditModule {

    @Provides
    @Singleton
    @Remote
    RedditDataSource provideRemoteRedditSource(RedditService redditService) {
        return new RedditRemoteSource(redditService);
    }

    @Provides
    @Singleton
    RedditRepository provideRedditRepository(@Remote RedditDataSource redditDataSource) {
        return new RedditRepository(redditDataSource);
    }
}