package com.harperjr.reviewsviewer;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;

@Module(includes = NetworkModule.class)
public class AppModule {
    private final Context context;

    public AppModule(@NonNull final Context context) {
        this.context = context;
    }
}
