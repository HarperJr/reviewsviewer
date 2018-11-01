package com.harperjr.reviewsviewer.localdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.harperjr.reviewsviewer.BuildConfig;
import com.harperjr.reviewsviewer.localdb.room.dao.ReviewDao;
import com.harperjr.reviewsviewer.localdb.room.entity.ReviewEntity;

@Database(version = BuildConfig.DB_VERSION, exportSchema = false, entities = {
        ReviewEntity.class,
})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ReviewDao reviewDao();
}