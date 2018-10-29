package com.harperjr.reviewsviewer.mvp;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.harperjr.reviewsviewer.api.nyt.Review;
import com.harperjr.reviewsviewer.model.MovieReview;

public interface ActivityView extends MvpView {
    void switchToDetailed(@NonNull final MovieReview review);
}
