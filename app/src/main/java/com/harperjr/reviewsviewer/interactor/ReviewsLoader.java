package com.harperjr.reviewsviewer.interactor;

import com.harperjr.reviewsviewer.BuildConfig;
import com.harperjr.reviewsviewer.api.ApiService;
import com.harperjr.reviewsviewer.api.mapper.ReviewMapper;
import com.harperjr.reviewsviewer.localdb.base.DbTransaction;
import com.harperjr.reviewsviewer.localdb.repository.ReviewRepository;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ReviewsLoader {

    private static final String API_KEY = BuildConfig.API_KEY;

    private ApiService apiService;
    private DbTransaction dbTransaction;
    private ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Inject
    ReviewsLoader(ApiService apiService, DbTransaction dbTransaction, ReviewRepository reviewRepository) {
        this.apiService = apiService;
        this.dbTransaction = dbTransaction;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Получает данные о ревьюшках, начиная с указанной позиции "offset", т.е учитывает уже загруженные ревью
     * @param offset
     * @return
     */
    public Observable<Result> getReviewsByOffset(int offset) {
        return apiService.getReviews(offset, API_KEY)
                .map(com.harperjr.reviewsviewer.api.model.Result::getReviews)
                .map(reviewMapper::entityListToModelList)
                .map(Result::new);
    }

    /**
     * Тоже получает данные о ревьюшках, как и getReviewsByOffset, но с возможностью поиска
     * @param offset
     * @param query
     * @return
     */
    public Observable<Result> searchReviewsByOffset(int offset, String query) {
        return apiService.searchReviews(offset, query, API_KEY)
                .map(com.harperjr.reviewsviewer.api.model.Result::getReviews)
                .map(reviewMapper::entityListToModelList)
                .map(Result::new);
    }

    public Observable<Result> getReviewsFromCacheByOffset(/*Unused for a while*/ int offset) {
        return Observable.fromCallable(() -> dbTransaction.callInTx(() -> reviewRepository.getReviews()))
                .map(Result::new);
    }

    public class Result {
        private final List<MovieReview> movieReviews;

        public Result(List<MovieReview> movieReviews) {
            this.movieReviews = movieReviews;
        }

        public List<MovieReview> getMovieReviews() {
            return movieReviews;
        }
    }
}
