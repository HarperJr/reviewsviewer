package com.harperjr.reviewsviewer.ui.reviews.favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.ui.view.ReviewsRecyclerView;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsRecyclerAdapter;

import java.util.List;


public class FavoritesFragment extends MvpAppCompatFragment implements FavoritesView {

    private ReviewsRecyclerView reviewsRecyclerView;
    private ReviewsRecyclerAdapter favoritesAdapter;

    private TextView noticeText;

    @InjectPresenter
    FavoritesPresenter favoritesPresenter;

    public FavoritesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        this.noticeText = view.findViewById(R.id.notice);
        this.reviewsRecyclerView = view.findViewById(R.id.favorites_recycler_view);
        this.favoritesAdapter = new ReviewsRecyclerAdapter();
        this.favoritesAdapter.setInteractionListener(new ReviewsRecyclerAdapter.InteractionListener() {
            @Override
            public void onReadMoreButtonClicked(@NonNull MovieReview movieReview) {

            }

            @Override
            public void onAddToFavoritesButtonClicked(@NonNull MovieReview movieReview) {

            }
        });
        this.reviewsRecyclerView.setAdapter(favoritesAdapter);

        return view;
    }

    @Override
    public void addFavorites(@NonNull List<MovieReview> reviews) {
        favoritesAdapter.addReviews(reviews);
    }

    @Override
    public void removeFromFavorites(MovieReview review) {

    }

    @Override
    public void showNotice(boolean show) {
        noticeText.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
