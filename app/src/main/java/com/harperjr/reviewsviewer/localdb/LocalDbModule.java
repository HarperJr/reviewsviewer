package com.harperjr.reviewsviewer.localdb;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.harperjr.reviewsviewer.BuildConfig;
import com.harperjr.reviewsviewer.localdb.base.DbTransaction;
import com.harperjr.reviewsviewer.localdb.room.base.DbTransactionImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        RepositoryModule.class,
        MapperModule.class
})
public class LocalDbModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, BuildConfig.DB_NAME)
                .build();
    }

    @Provides
    @Singleton
    public DbTransaction provideDbTransaction(AppDatabase appDatabase) {
        return new DbTransactionImpl(appDatabase);
    }
}
