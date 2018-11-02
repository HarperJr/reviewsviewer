package com.harperjr.reviewsviewer.ui.reviews.favorite;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.harperjr.reviewsviewer.App;
import com.harperjr.reviewsviewer.interactor.ReviewsLoader;
import com.harperjr.reviewsviewer.interactor.ReviewsWriter;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritesView> {

    private final List<MovieReview> favoriteReviews = new ArrayList<>();

    private Disposable loadDisposable = Disposables.disposed();

    private ReviewsLoader reviewsLoader = App.getDaggerAppComponent().getReviewLoader();
    private ReviewsWriter reviewsWriter = App.getDaggerAppComponent().getReviewsWriter();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadReviews();
    }

    public void loadReviews() {
        loadDisposable = reviewsLoader.getReviewsFromCacheByOffset(favoriteReviews.size())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    final List<MovieReview> reviews = result.getMovieReviews();
                    getViewState().showNotice(reviews.isEmpty());
                    getViewState().addFavorites(reviews);
                    },
                        throwable -> getViewState().showNotice(true)
                );
    }

    public void removeReview(MovieReview review) {
        //reviewsWriter.removeReview(review)
    }
}
