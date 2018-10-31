package com.harperjr.reviewsviewer.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.harperjr.reviewsviewer.ui.reviews.fragment.DetailedReviewFragment;
import com.harperjr.reviewsviewer.ui.reviews.fragment.ReviewsFragment;

public class ReviewsPagerAdapter extends FragmentPagerAdapter {

    public static final int REVIEWS_PAGE = 0;
    public static final int DETAILED_REVIEW_PAGE = 1;
    private static final int PAGES_COUNT = 2;
    private ReviewsFragment reviewsFragment;
    private DetailedReviewFragment detailedReviewFragment;

    public ReviewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setReviewsFragment(final ReviewsFragment reviewsFragment) {
        this.reviewsFragment = reviewsFragment;
    }

    public void setDetailedReviewFragment(DetailedReviewFragment detailedReviewFragment) {
        this.detailedReviewFragment = detailedReviewFragment;
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Override
    public Fragment getItem(int i) {
        return i == REVIEWS_PAGE ? reviewsFragment : detailedReviewFragment;
    }
}
