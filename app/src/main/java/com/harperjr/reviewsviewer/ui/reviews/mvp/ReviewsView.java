package com.harperjr.reviewsviewer.ui.reviews.mvp;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.List;

public interface ReviewsView extends MvpView {
    void addReviews(@NonNull final List<MovieReview> movieReviews);
    void clearReviews();
    void setLoading(boolean loading);
}
