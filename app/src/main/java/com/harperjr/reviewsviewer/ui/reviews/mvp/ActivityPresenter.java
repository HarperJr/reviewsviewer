package com.harperjr.reviewsviewer.ui.reviews.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.harperjr.reviewsviewer.model.MovieReview;

@InjectViewState
public class ActivityPresenter extends MvpPresenter<ActivityView> {
    public void navigateToDetailed(final MovieReview movieReview) {
        getViewState().navigateToDetailed(movieReview);
    }
}
