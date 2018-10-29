package com.harperjr.reviewsviewer.mvp;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.harperjr.reviewsviewer.api.nyt.Review;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.List;

public interface ReviewsView extends MvpView {
    void addReviews(@NonNull final List<MovieReview> reviews);
    void clearReviews();
    void setLoading(boolean loading);
}
