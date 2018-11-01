package com.harperjr.reviewsviewer.model.base;

public interface ModelWithId<ID> {
    ID getId();

    void setId(ID id);
}
