package com.harperjr.reviewsviewer.localdb.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.localdb.repository.base.BaseRepository;

import java.util.List;

public interface ReviewRepository extends BaseRepository<MovieReview> {

    @Nullable
    MovieReview getById(long id);

    @Nullable
    MovieReview getByTitle(String title);

    @NonNull
    List<MovieReview> getMatchTitle(String title);

    @NonNull
    List<MovieReview> getReviews();

    void deleteAll();
}
