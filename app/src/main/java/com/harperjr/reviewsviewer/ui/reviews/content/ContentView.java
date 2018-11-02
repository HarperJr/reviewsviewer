package com.harperjr.reviewsviewer.ui.reviews.content;

import com.arellomobile.mvp.MvpView;
import com.harperjr.reviewsviewer.model.MovieReview;

public interface ContentView extends MvpView {
    void navigateToDetailed(final MovieReview movieReview);
}
