package com.harperjr.reviewsviewer;

import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.interactor.ReviewsLoader;
import com.harperjr.reviewsviewer.interactor.ReviewsWriter;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(@NonNull final ReviewsPresenter presenter);
    ReviewsLoader getReviewLoader();
    ReviewsWriter getReviewsWriter();
}
