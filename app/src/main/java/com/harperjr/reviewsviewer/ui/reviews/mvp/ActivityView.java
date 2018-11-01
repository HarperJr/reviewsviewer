package com.harperjr.reviewsviewer.ui.reviews.mvp;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.harperjr.reviewsviewer.model.MovieReview;

public interface ActivityView extends MvpView {
    void navigateToDetailed(@NonNull final MovieReview movieReview);
}
