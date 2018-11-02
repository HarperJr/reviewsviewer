package com.harperjr.reviewsviewer.localdb.room.repository;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.harperjr.reviewsviewer.localdb.AppDatabase;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.localdb.repository.ReviewRepository;
import com.harperjr.reviewsviewer.localdb.room.dao.ReviewDao;
import com.harperjr.reviewsviewer.localdb.room.entity.ReviewEntity;
import com.harperjr.reviewsviewer.localdb.room.mapper.ReviewMapper;
import com.harperjr.reviewsviewer.localdb.room.mapper.base.BaseMapper;
import com.harperjr.reviewsviewer.localdb.room.repository.base.BaseRepositoryImpl;
import java.util.List;
import javax.inject.Inject;

public class ReviewRepositoryImpl extends BaseRepositoryImpl<MovieReview, ReviewEntity> implements ReviewRepository {

    private final ReviewMapper reviewMapper;

    @Inject
    public ReviewRepositoryImpl(AppDatabase appDatabase, ReviewMapper reviewMapper) {
        super(appDatabase);
        this.reviewMapper = reviewMapper;
    }

    @Override
    protected BaseMapper<MovieReview, ReviewEntity> baseMapper() {
        return reviewMapper;
    }

    @Override
    protected ReviewDao dao() {
        return appDatabase.reviewDao();
    }

    @Nullable
    @Override
    public MovieReview getById(long id) {
        return baseMapper().entityToModel(dao().getById(id));
    }

    @Nullable
    @Override
    public MovieReview getByTitle(String title) {
        return baseMapper().entityToModel(dao().getReviewByTitle(title));
    }

    @NonNull
    @Override
    public List<MovieReview> getMatchTitle(String title) {
        return baseMapper().entityListToModelList(dao().getReviewsMatchDisplayTitle(title));
    }

    @NonNull
    @Override
    public List<MovieReview> getReviews() {
        return baseMapper().entityListToModelList(dao().getReviews());
    }

    @Override
    public void deleteAll() {

    }
}
