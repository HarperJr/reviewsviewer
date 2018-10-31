package com.harperjr.reviewsviewer.model.mapper;

import com.harperjr.reviewsviewer.api.model.Review;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {

    private MovieReview map(final Review review) {
        final MovieReview movieReview = new MovieReview(review.getDisplayTitle());

        movieReview.setHeadLine(review.getHeadline());
        movieReview.setByLine(review.getByline());
        movieReview.setSummaryShort(review.getSummaryShort());
        movieReview.setPublicationDate(review.getPublicationDate());

        final String multimediaUrl = review.getMultimedia() != null ? review.getMultimedia().getSource() : null;
        movieReview.setMultimediaUri(multimediaUrl);

        movieReview.setLinkUri(review.getLink().getUrl());

        return movieReview;
    }

    public List<MovieReview> mapList(final List<Review> reviews) {
        final List<MovieReview> movieReviews = new ArrayList<>();
        for (final Review review : reviews) {
            movieReviews.add(map(review));
        }
        return movieReviews;
    }
}
