package com.harperjr.reviewsviewer.localdb.base;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;

public interface DbTransaction {

    void beginTransaction();

    void endTransaction();

    void setTransactionSuccessful();

    <T> T callInTx(@NonNull Callable<T> callable);

    void callInTx(@NonNull Runnable runnable);
}
