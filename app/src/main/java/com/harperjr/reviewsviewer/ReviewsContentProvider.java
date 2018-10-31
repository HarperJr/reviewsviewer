package com.harperjr.reviewsviewer;

import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.model.MovieReview;

import io.reactivex.Observable;

public interface ReviewsContentProvider {
    @ NonNull
    Observable<MovieReview> getReviews();
    @ NonNull
    Observable<MovieReview> searchReviews();
}
