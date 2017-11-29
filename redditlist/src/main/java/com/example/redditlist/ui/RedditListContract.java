package com.example.redditlist.ui;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.vn008xw.myapplication.data.vo.RedditDataChild;
import com.example.vn008xw.myapplication.data.vo.RedditPost;
import com.example.vn008xw.myapplication.ui.base.BasePresenterContract;
import com.example.vn008xw.myapplication.ui.base.BaseView;

import java.util.List;

public interface RedditListContract {

    interface View extends BaseView {
        void showEntries(@NonNull List<RedditDataChild> entries);

        void showImage(@NonNull RedditPost post, @NonNull ImageView imageView);

        void showNoImage();
    }

    interface Presenter extends BasePresenterContract<View> {
        void getNextGroupOfPosts();

        void getPosts(@NonNull String after);

        void openImage(@NonNull RedditPost post,
                       @NonNull ImageView imageView);

        void openRedditDetail(@NonNull RedditPost redditPost);

        void refresh();
    }
}
