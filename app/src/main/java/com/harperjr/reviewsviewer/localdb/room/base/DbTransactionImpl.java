package com.harperjr.reviewsviewer.localdb.room.base;

import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.localdb.AppDatabase;
import com.harperjr.reviewsviewer.localdb.base.DbTransaction;

import java.util.concurrent.Callable;

public class DbTransactionImpl implements DbTransaction {

    private final AppDatabase appDatabase;

    public DbTransactionImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void beginTransaction() {
        appDatabase.beginTransaction();
    }

    @Override
    public void endTransaction() {
        appDatabase.endTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
        appDatabase.setTransactionSuccessful();
    }

    @Override
    public <T> T callInTx(@NonNull Callable<T> callable) {
        return appDatabase.runInTransaction(callable);
    }

    @Override
    public void callInTx(@NonNull Runnable runnable) {
        appDatabase.runInTransaction(runnable);
    }
}
