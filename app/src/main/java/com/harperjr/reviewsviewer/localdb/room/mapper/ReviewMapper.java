package com.harperjr.reviewsviewer.localdb.room.mapper;

import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.localdb.room.entity.ReviewEntity;
import com.harperjr.reviewsviewer.localdb.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper extends BaseMapper<MovieReview, ReviewEntity> {
}
