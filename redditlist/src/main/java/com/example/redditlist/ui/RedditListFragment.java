package com.example.redditlist.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.redditlist.R;
import com.example.redditlist.utils.ComponentProvider;
import com.example.vn008xw.myapplication.data.vo.RedditDataChild;
import com.example.vn008xw.myapplication.data.vo.RedditPost;
import com.example.vn008xw.myapplication.ui.base.BaseFragment;

import java.util.List;

public class RedditListFragment
        extends BaseFragment<RedditListContract.Presenter>
        implements RedditListContract.View {


    public RedditListFragment() {
        // Required empty public constructor
    }


    public static RedditListFragment newInstance(String param1, String param2) {
        RedditListFragment fragment = new RedditListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentProvider.getRedditComponent(getActivity())
                .inject(this);
        super.onCreate(savedInstanceState);

        getPresenter().getNextGroupOfPosts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_list, container, false);
    }

    @Override
    public void showLoading(boolean isLoading) {

    }

    @Override
    public void showEntries(@NonNull List<RedditDataChild> entries) {

    }

    @Override
    public void showImage(@NonNull RedditPost post, @NonNull ImageView imageView) {

    }

    @Override
    public void showNoImage() {

    }
}
