package com.harperjr.reviewsviewer;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.harperjr.reviewsviewer.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String NYT_REVIEWS_URL = "https://api.nytimes.com/svc/movies/v2/";
    private static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYT_REVIEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
