package com.example.vn008xw.myapplication.dagger.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.vn008xw.myapplication.BuildConfig;
import com.example.vn008xw.myapplication.data.api.RedditService;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    private static final long DISK_CACHE_SIZE = 20 * 1024 * 1024;

    // We don't need a singleton of gson as there's too much overhead
    // for an object that isn't state dependent
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        if (BuildConfig.DEBUG) {
            return new HttpLoggingInterceptor(Timber::d)
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new HttpLoggingInterceptor(Timber::d)
                .setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        return new Cache(context.getCacheDir(), DISK_CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@NonNull Cache cache,
                                     @NonNull HttpLoggingInterceptor interceptor) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .writeTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }

    // Strings are immutable, no sense in making it a singleton
    @Provides
    @Named("Endpoint")
    String provideBaseUrl() {
        return BuildConfig.REDDIT_ENDPOINT;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull Gson gson,
                             @NonNull OkHttpClient okHttpClient,
                             @NonNull @Named("Endpoint") String endpoint) {
        return new Retrofit.Builder().client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(endpoint)
                .build();
    }

    @Provides
    @Singleton
    RedditService provideRedditService(Retrofit retrofit) {
        return retrofit.create(RedditService.class);
    }
}