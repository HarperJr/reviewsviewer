package com.harperjr.reviewsviewer.ui.reviews.content;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.ui.reviews.ActivityEventListener;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewFragment;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewsFragment;
import com.harperjr.reviewsviewer.ui.view.ReviewsViewPager;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsPagerAdapter;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsRecyclerAdapter;

public class ContentFragment extends MvpAppCompatFragment implements ContentView, ActivityEventListener {

    private ReviewsViewPager viewPager;
    private ReviewsPagerAdapter reviewsPagerAdapter;

    private ReviewsFragment reviewsFragment;
    private ReviewFragment reviewFragment;

    @InjectPresenter
    ContentPresenter contentPresenter;

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_content, container, false);

        this.reviewsFragment = new ReviewsFragment();
        this.reviewsFragment.setInteractionListener(new ReviewsRecyclerAdapter.InteractionListener() {
            @Override
            public void onReadMoreButtonClicked(@NonNull MovieReview movieReview) {
                contentPresenter.navigateToDetailed(movieReview);
            }

            @Override
            public void onAddToFavoritesButtonClicked(@NonNull MovieReview movieReview) {
                reviewsFragment.storeReview(movieReview);
            }
        });
        this.reviewFragment = new ReviewFragment();
        this.reviewsPagerAdapter = new ReviewsPagerAdapter(getActivity().getSupportFragmentManager());
        this.reviewsPagerAdapter.setReviewsFragment(reviewsFragment);
        this.reviewsPagerAdapter.setReviewFragment(reviewFragment);

        this.viewPager = view.findViewById(R.id.pager);
        this.viewPager.setAdapter(reviewsPagerAdapter);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int page, float v, int i1) {
            }

            @Override
            public void onPageSelected(int page) {
                final boolean focusedOnDetailedPage = page == ReviewsPagerAdapter.DETAILED_REVIEW_PAGE;
                viewPager.setScrollable(focusedOnDetailedPage);

            }

            @Override
            public void onPageScrollStateChanged(int page) {
            }
        });

        return view;
    }

    @Override
    public void navigateToDetailed(MovieReview movieReview) {
        this.viewPager.setCurrentItem(ReviewsPagerAdapter.DETAILED_REVIEW_PAGE);
        this.reviewFragment.load(movieReview);
    }

    @Override
    public void onSearching(String query) {
        this.reviewsFragment.onSearching(query);
    }
}
