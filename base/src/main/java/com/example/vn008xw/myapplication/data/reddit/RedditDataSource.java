package com.example.vn008xw.myapplication.data.reddit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.vn008xw.myapplication.data.vo.RedditData;
import com.example.vn008xw.myapplication.data.vo.RedditPost;

import io.reactivex.Observable;

public interface RedditDataSource {

    Observable<RedditData> getEntries(@NonNull String after,
                                      int limit);

    void cachePost(@NonNull RedditPost post);

    @Nullable
    RedditPost getCachedPost(@NonNull String postId);

    void refresh();
}