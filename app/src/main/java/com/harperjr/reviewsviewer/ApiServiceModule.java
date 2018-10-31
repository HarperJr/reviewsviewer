package com.harperjr.reviewsviewer;

import com.harperjr.reviewsviewer.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    public ApiService provideApiService(final Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
