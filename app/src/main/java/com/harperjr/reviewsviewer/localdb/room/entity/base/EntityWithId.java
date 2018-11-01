package com.harperjr.reviewsviewer.localdb.room.entity.base;

public interface EntityWithId<ID> {

    ID getId();

    void setId(ID id);
}
