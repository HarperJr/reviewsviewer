package com.harperjr.reviewsviewer.ui.reviews.favorite;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.List;

public interface FavoritesView extends MvpView {
    void addFavorites(@NonNull List<MovieReview> reviews);
    void removeFromFavorites(MovieReview review);
    void showNotice(boolean show);
}
