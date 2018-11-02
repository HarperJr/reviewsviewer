package com.harperjr.reviewsviewer.ui.reviews.content;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.harperjr.reviewsviewer.model.MovieReview;

@InjectViewState
public class ContentPresenter extends MvpPresenter<ContentView> {


    public void navigateToDetailed(@NonNull final MovieReview movieReview) {
        getViewState().navigateToDetailed(movieReview);
    }
}
