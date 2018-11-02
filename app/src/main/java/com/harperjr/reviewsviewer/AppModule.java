package com.harperjr.reviewsviewer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.localdb.LocalDbModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        NetworkModule.class,
        LocalDbModule.class
})
public class AppModule {
    private final Context context;

    public AppModule(@NonNull final Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return this.context;
    }
}
