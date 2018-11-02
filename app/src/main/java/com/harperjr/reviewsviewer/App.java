package com.harperjr.reviewsviewer;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.harperjr.reviewsviewer.api.ApiService;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static AppComponent daggerAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {});
        daggerAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getDaggerAppComponent() {
        return daggerAppComponent;
    }
}
