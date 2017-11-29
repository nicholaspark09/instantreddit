package com.example.redditlist.ui;


import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;

import com.example.vn008xw.myapplication.data.NetworkObserver;
import com.example.vn008xw.myapplication.data.NetworkResource;
import com.example.vn008xw.myapplication.data.reddit.RedditRepository;
import com.example.vn008xw.myapplication.data.vo.RedditData;
import com.example.vn008xw.myapplication.data.vo.RedditPost;
import com.example.vn008xw.myapplication.ui.base.BasePresenter;
import com.jakewharton.rxrelay2.PublishRelay;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class RedditListPresenter extends BasePresenter<RedditListContract.View>
        implements RedditListContract.Presenter {

    private static final int LIMIT = 10;

    @VisibleForTesting
    @NonNull
    final Scheduler mIoThread;
    @VisibleForTesting
    @NonNull
    final Scheduler mMainThread;

    @VisibleForTesting
    @NonNull
    final RedditRepository mRedditRepository;

    private final PublishRelay<Long> retryRequest = PublishRelay.create();

    private String after = "";

    @Inject
    public RedditListPresenter(@Named("IoThread") Scheduler ioThread,
                               @Named("MainThread") Scheduler mainThread,
                               RedditRepository redditRepository) {
        mIoThread = ioThread;
        mMainThread = mainThread;
        mRedditRepository = redditRepository;
    }

    @Override
    public void getNextGroupOfPosts() {
        final Disposable disposable =
                mRedditRepository.getEntries(after, LIMIT)
                        .subscribeOn(mIoThread)
                        .startWith(new NetworkResource.Loading<>())
                        .onErrorReturn(throwable -> new NetworkResource.Error<>())
                        .observeOn(mMainThread)
                        .retryWhen(throwableObservable -> retryRequest)
                        .subscribe(new NetworkObserver<RedditData>() {
                            @Override
                            public void onSuccess(@NotNull NetworkResource.Success<RedditData> result) {

                            }

                            @Override
                            public void onError(@NotNull NetworkResource.Error<RedditData> result) {

                            }

                            @Override
                            public void onLoading() {
                                getView().showLoading(true);
                            }

                            @Override
                            public void onIdle() {

                            }
                        });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getPosts(@NonNull String after) {

    }

    @Override
    public void openImage(@NonNull RedditPost post, @NonNull ImageView imageView) {

    }

    @Override
    public void openRedditDetail(@NonNull RedditPost redditPost) {

    }

    @Override
    public void refresh() {

    }
}