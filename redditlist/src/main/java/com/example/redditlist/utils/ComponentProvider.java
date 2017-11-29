package com.example.redditlist.utils;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.redditlist.dagger.component.DaggerRedditComponent;
import com.example.redditlist.dagger.component.RedditComponent;

public final class ComponentProvider {

    private static RedditComponent mRedditComponent;

    private ComponentProvider() {
        throw new AssertionError("No instances");
    }

    public static RedditComponent getRedditComponent(@NonNull Context context) {
        if (mRedditComponent == null) {
            mRedditComponent = DaggerRedditComponent.builder()
                    .bindContext(context)
                    .build();
        }
        return mRedditComponent;
    }
}
