package com.harperjr.reviewsviewer.localdb.room.dao.base;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.List;

public interface BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Entity entity);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long[] insert(List<Entity> entities);

    @Delete
    void delete(Entity entity);

    @Delete
    void delete(List<Entity> entities);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void update(Entity entity);
}
