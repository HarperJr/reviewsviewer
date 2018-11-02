package com.harperjr.reviewsviewer.ui.reviews.content.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.ui.reviews.ActivityEventListener;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsRecyclerAdapter;
import com.harperjr.reviewsviewer.ui.view.ReviewsRecyclerView;

import java.util.List;


public class ReviewsFragment extends MvpAppCompatFragment implements ReviewsView{

    private SwipeRefreshLayout swipeRefreshLayout;
    private ReviewsRecyclerView reviewsRecyclerView;
    private ReviewsRecyclerAdapter reviewsRecyclerAdapter;
    private ReviewsRecyclerAdapter.InteractionListener interactionListener;

    @InjectPresenter(type = PresenterType.GLOBAL)
    public ReviewsPresenter reviewsPresenter;

    public ReviewsFragment() {
    }

    public void setInteractionListener(ReviewsRecyclerAdapter.InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater.inflate(R.layout.fragment_reviews, container, false);

        this.reviewsRecyclerAdapter = new ReviewsRecyclerAdapter();
        this.reviewsRecyclerAdapter.setInteractionListener(this.interactionListener);
        this.swipeRefreshLayout = fragmentView.findViewById(R.id.swipe_layout);
        this.swipeRefreshLayout.setOnRefreshListener(() -> reviewsPresenter.refreshReviews());
        this.reviewsRecyclerView = fragmentView.findViewById(R.id.reviews_recycler);
        this.reviewsRecyclerView.setAdapter(reviewsRecyclerAdapter);
        this.reviewsRecyclerView.setOnScrolledListener(() -> reviewsPresenter.loadReviews());

        return fragmentView;
    }

    public void onSearching(String query) {
        reviewsPresenter.setSearchingQuery(query);
        reviewsPresenter.refreshReviews();
    }

    @Override
    public void addReviews(@NonNull List<MovieReview> movieReviews) {
        this.reviewsRecyclerAdapter.addReviews(movieReviews);
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

    @Override
    public void showToast(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_LONG).show();
    }

    public void storeReview(MovieReview movieReview) {
        reviewsPresenter.storeReview(movieReview);
    }
}
