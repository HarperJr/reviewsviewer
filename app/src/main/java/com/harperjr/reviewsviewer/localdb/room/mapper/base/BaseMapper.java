package com.harperjr.reviewsviewer.localdb.room.mapper.base;

import android.support.annotation.NonNull;

import java.util.List;

public interface BaseMapper<Model, Entity> {

    @NonNull
    Model entityToModel(@NonNull Entity entity);

    @NonNull
    Entity modelToEntity(@NonNull Model model);

    @NonNull
    List<Model> entityListToModelList(@NonNull List<Entity> entities);

    @NonNull
    List<Entity> modelListToEntityList(@NonNull List<Model> models);
}
