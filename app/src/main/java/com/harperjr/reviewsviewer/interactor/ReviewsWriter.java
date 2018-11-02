package com.harperjr.reviewsviewer.interactor;

import android.support.annotation.NonNull;

import com.harperjr.reviewsviewer.localdb.base.DbTransaction;
import com.harperjr.reviewsviewer.localdb.repository.ReviewRepository;
import com.harperjr.reviewsviewer.model.MovieReview;
import javax.inject.Inject;

import io.reactivex.Completable;

public class ReviewsWriter {

    private final DbTransaction dbTransaction;
    private final ReviewRepository reviewRepository;

    @Inject
    public ReviewsWriter(DbTransaction dbTransaction, ReviewRepository reviewRepository) {
        this.dbTransaction = dbTransaction;
        this.reviewRepository = reviewRepository;
    }

    public Completable storeReview(@NonNull MovieReview movieReview) {
        return Completable.fromAction(() -> dbTransaction.callInTx(() -> {
            reviewRepository.insert(movieReview);
        }));
    }

}
