package com.harperjr.reviewsviewer.ui.reviews.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.harperjr.reviewsviewer.api.ApiService;
import com.harperjr.reviewsviewer.App;
import com.harperjr.reviewsviewer.api.model.Result;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.model.mapper.ReviewMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;

@InjectViewState
public class ReviewsPresenter extends MvpPresenter<ReviewsView> {
    private static final int REVIEWS_PACKAGE_SIZE = 20;
    private static final String API_KEY = "64ebfa0fdec6427887d26a483ff8c286";

    private Disposable receiveReviewsDisposable;
    private Disposable searchReviewsDisposable;

    @Inject
    ApiService apiService;

    private final ReviewMapper reviewMapper = new ReviewMapper();
    private final List<MovieReview> reviews = new ArrayList<>();
    private final List<MovieReview> searchedReviews = new ArrayList<>();

    private String searchingQuery;

    public ReviewsPresenter() {
        App.getDaggerAppComponent().inject(this);
        this.receiveReviewsDisposable = Disposables.disposed();
        this.searchReviewsDisposable = Disposables.disposed();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadReviews();
    }

    public void loadReviews() {
        final ReviewsView viewState = getViewState();
        if (searchingQuery == null) {
            if (!searchedReviews.isEmpty()) {
                searchedReviews.clear();
                viewState.clearReviews();
                if (!reviews.isEmpty()) {
                    viewState.addReviews(reviews);
                    return;
                }
            }
            if (reviews.size() % REVIEWS_PACKAGE_SIZE != 0) {
                return;
            }

            receiveReviewsDisposable = apiService.getReviews(reviews.size(), API_KEY)
                    .map(result -> reviewMapper.mapList(result.getReviews()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {

                    }, throwable -> {

                    });
        } else {
            if (searchedReviews.size() % REVIEWS_PACKAGE_SIZE != 0) {
                return;
            }
            viewState.setLoading(true);
            searchReviewsDisposable = apiService.searchReviews(searchedReviews.size(), searchingQuery, API_KEY)
                    .map(result -> reviewMapper.mapList(result.getReviews()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {

                    }, throwable -> {

                    });
        }
    }

    public void refreshReviews() {
        final ReviewsView viewState = getViewState();

        reviews.clear();
        searchedReviews.clear();

        viewState.clearReviews();
        loadReviews();
    }

    public void setSearchingQuery(@Nullable final String searchingQuery) {
        this.searchingQuery = searchingQuery;
    }

    private final Callback<Result> getReviewsCallback = new Callback<Result>() {
        @Override
        public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
            final ReviewsView viewState = getViewState();
            final List<MovieReview> incomingReviews = new ReviewMapper().mapList(response.body().getReviews());

            reviews.addAll(incomingReviews);

            viewState.addReviews(incomingReviews);
            viewState.setLoading(false);
        }

        @Override
        public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
            getViewState().setLoading(false);
        }
    };

    private final Callback<Result> searchReviewsCallback = new Callback<Result>() {
        @Override
        public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
            final ReviewsView viewState = getViewState();
            final List<MovieReview> incomingReviews = new ReviewMapper().mapList(response.body().getReviews());
            searchedReviews.addAll(incomingReviews);

            viewState.addReviews(incomingReviews);
            viewState.setLoading(false);
        }

        @Override
        public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
            getViewState().setLoading(false);
        }
    };
}
