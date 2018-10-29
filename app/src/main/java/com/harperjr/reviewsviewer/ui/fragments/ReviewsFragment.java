package com.harperjr.reviewsviewer.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.mvp.ReviewsPresenter;
import com.harperjr.reviewsviewer.mvp.ReviewsView;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.ui.adapters.ReviewsRecyclerAdapter;
import com.harperjr.reviewsviewer.ui.views.ReviewsRecyclerView;

import java.util.List;


public class ReviewsFragment extends MvpAppCompatFragment implements ReviewsView, ActivityEventListener {

    @InjectPresenter(type = PresenterType.GLOBAL)
    public ReviewsPresenter reviewsPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ReviewsRecyclerView reviewsRecyclerView;
    private ReviewsRecyclerAdapter reviewsRecyclerAdapter;

    private ReviewsRecyclerAdapter.InteractionListener interactionListener;

    public ReviewsFragment() {
    }

    public void setInteractionListener(ReviewsRecyclerAdapter.InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reviewsRecyclerAdapter = new ReviewsRecyclerAdapter();
        reviewsRecyclerAdapter.setInteractionListener(this.interactionListener);

        this.swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        this.swipeRefreshLayout.setOnRefreshListener(() -> reviewsPresenter.refreshReviews());

        this.reviewsRecyclerView = view.findViewById(R.id.reviews_recycler);
        this.reviewsRecyclerView.setAdapter(reviewsRecyclerAdapter);
        this.reviewsRecyclerView.setOnScrolledListener(() -> reviewsPresenter.loadReviews());
    }

    @Override
    public void onSearching(String query) {
        reviewsPresenter.setSearchingQuery(query);
        reviewsPresenter.refreshReviews();
    }

    @Override
    public void addReviews(@NonNull List<MovieReview> reviews) {
        this.reviewsRecyclerAdapter.addReviews(reviews);
    }

    @Override
    public void clearReviews() {
        this.reviewsRecyclerAdapter.clearReviews();
    }

    @Override
    public void setLoading(boolean loading) {
        this.swipeRefreshLayout.setRefreshing(loading);
        this.reviewsRecyclerView.setLoading(loading);
    }
}
