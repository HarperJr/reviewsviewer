package com.harperjr.reviewsviewer.localdb.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.harperjr.reviewsviewer.localdb.room.dao.base.BaseDao;
import com.harperjr.reviewsviewer.localdb.room.entity.ReviewEntity;

import java.util.List;

@Dao
public interface ReviewDao extends BaseDao<ReviewEntity> {

    @Query("SELECT * FROM Review WHERE id = :id")
    ReviewEntity getById(long id);

    @Query("SELECT * FROM Review WHERE linkUri = :linkUri")
    ReviewEntity getReviewByLinkUri(String linkUri);

    @Query("SELECT * FROM Review WHERE multimediaUri = :multimediaUri")
    ReviewEntity getReviewByMultimediaUri(String multimediaUri);

    @Query("SELECT * FROM Review WHERE displayTitle LIKE :displayTitle")
    List<ReviewEntity> getReviewsMatchDisplayTitle(String displayTitle);
}
