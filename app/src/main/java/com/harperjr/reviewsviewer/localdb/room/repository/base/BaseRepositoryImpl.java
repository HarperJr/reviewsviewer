package com.harperjr.reviewsviewer.localdb.room.repository.base;

import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.localdb.AppDatabase;
import com.harperjr.reviewsviewer.model.base.ModelWithId;
import com.harperjr.reviewsviewer.localdb.repository.base.BaseRepository;
import com.harperjr.reviewsviewer.localdb.room.dao.base.BaseDao;
import com.harperjr.reviewsviewer.localdb.room.entity.base.EntityWithId;
import com.harperjr.reviewsviewer.localdb.room.mapper.base.BaseMapper;

import java.util.List;

public abstract class BaseRepositoryImpl<Model extends ModelWithId, Entity extends EntityWithId> implements BaseRepository<Model> {

    protected final AppDatabase appDatabase;

    public BaseRepositoryImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    protected abstract BaseMapper<Model, Entity> baseMapper();

    protected abstract BaseDao<Entity> dao();

    @Override
    public void insert(@NonNull Model model) {
        dao().insert(baseMapper().modelToEntity(model));
    }

    @Override
    public void insert(@NonNull List<Model> models) {
        dao().insert(baseMapper().modelListToEntityList(models));
    }

    @Override
    public void update(@NonNull Model model) {
        dao().update(baseMapper().modelToEntity(model));
    }

    @Override
    public void delete(@NonNull Model model) {
        dao().delete(baseMapper().modelToEntity(model));
    }
}
