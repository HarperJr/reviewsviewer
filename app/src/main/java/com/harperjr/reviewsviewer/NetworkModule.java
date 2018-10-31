package com.harperjr.reviewsviewer;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ApiServiceModule.class)
public class NetworkModule {
    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/movies/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
