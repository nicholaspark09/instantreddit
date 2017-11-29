package com.example.vn008xw.myapplication.data.reddit;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.example.vn008xw.myapplication.dagger.Remote;
import com.example.vn008xw.myapplication.data.NetworkResource;
import com.example.vn008xw.myapplication.data.vo.RedditData;
import com.example.vn008xw.myapplication.data.vo.RedditPost;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RedditRepository {

    @NonNull
    @VisibleForTesting
    final RedditDataSource mRemoteSource;

    @NonNull
    @VisibleForTesting
    final HashMap<String, RedditData> mCache = new HashMap<>();

    @Nullable
    @VisibleForTesting
    RedditPost mCachedPost;

    @VisibleForTesting boolean mCacheIsDirty = false;

    @Inject
    public RedditRepository(@Remote RedditDataSource dataSource) {
        mRemoteSource = dataSource;
    }


    public Observable<NetworkResource<RedditData>> getEntries(@NonNull String after, int limit) {
        if (!mCacheIsDirty && mCache.containsKey(after)) {
            return Observable.just(new NetworkResource.Success<>(mCache.get(after)));
        }
        return mRemoteSource.getEntries(after, limit)
                .doOnNext(redditData -> {
                    if (redditData != null) {
                        mCache.put(after, redditData);
                        mCacheIsDirty = false;
                    }
                }).flatMap(redditData ->
                        Observable.just(new NetworkResource.Success<>(redditData)));
    }


    public void cachePost(@NonNull RedditPost post) {
        mCachedPost = post;
    }

    @Nullable

    public RedditPost getCachedPost(@NonNull String postId) {
        return mCachedPost != null && mCachedPost.getId().equals(postId) ? mCachedPost : null;
    }

    public void refresh() {
        mCacheIsDirty = true;
    }
}