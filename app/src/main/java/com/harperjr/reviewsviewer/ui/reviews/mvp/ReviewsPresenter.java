package com.harperjr.reviewsviewer.ui.reviews.mvp;

import android.support.annotation.Nullable;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.harperjr.reviewsviewer.App;
import com.harperjr.reviewsviewer.api.ApiService;
import com.harperjr.reviewsviewer.interactor.ReviewsLoader;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ReviewsPresenter extends MvpPresenter<ReviewsView> {
    private static final int REVIEWS_PACKAGE_SIZE = 20;

    private final List<MovieReview> movieReviews = new ArrayList<>();
    private final List<MovieReview> searchedMovieReviews = new ArrayList<>();

    private Disposable loadingDisposable = Disposables.disposed();
    private Disposable searchingDisposable = Disposables.disposed();

    private String searchingQuery;

    @Inject
    ReviewsLoader reviewsLoader = App.getDaggerAppComponent().getReviewLoader();

    public ReviewsPresenter() {
        App.getDaggerAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadReviews();
    }

    public void loadReviews() {
        final ReviewsView viewState = getViewState();
        if (searchingQuery == null) {
            if (!searchedMovieReviews.isEmpty()) {
                searchedMovieReviews.clear();
                viewState.clearReviews();
                if (!movieReviews.isEmpty()) {
                    viewState.addReviews(movieReviews);
                    return;
                }
            }
            if (movieReviews.size() % REVIEWS_PACKAGE_SIZE != 0) {
                return;
            }
            getViewState().setLoading(true);
            loadingDisposable = reviewsLoader
                    .getReviewsByOffset(movieReviews.size())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> getViewState().setLoading(true))
                    .subscribe(result -> {
                        getViewState().addReviews(result.getMovieReviews());
                        getViewState().setLoading(false);
                    }, throwable -> Log.e("Retrofit", throwable.getMessage()));

        } else {
            if (searchedMovieReviews.size() % REVIEWS_PACKAGE_SIZE != 0) {
                return;
            }
            viewState.setLoading(true);
            searchingDisposable = reviewsLoader
                    .searchReviewsByOffset(searchedMovieReviews.size(), searchingQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> getViewState().setLoading(true))
                    .subscribe(result -> {
                        getViewState().addReviews(result.getMovieReviews());
                        getViewState().setLoading(false);
                    }, throwable -> Log.e("Retrofit", throwable.getMessage()));
        }
    }

    public void refreshReviews() {
        final ReviewsView viewState = getViewState();

        movieReviews.clear();
        searchedMovieReviews.clear();

        viewState.clearReviews();
        loadReviews();
    }

    public void setSearchingQuery(@Nullable final String searchingQuery) {
        this.searchingQuery = searchingQuery;
    }

}
