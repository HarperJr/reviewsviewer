package com.harperjr.reviewsviewer.localdb.repository.base;

import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.model.base.ModelWithId;

import java.util.List;

public interface BaseRepository<Model extends ModelWithId> extends Repository {

    void insert(@NonNull Model model);

    void insert(@NonNull List<Model> models);

    void update(@NonNull Model model);

    void delete(@NonNull Model model);
}
