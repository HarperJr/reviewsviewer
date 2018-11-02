package com.harperjr.reviewsviewer.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewFragment;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewsFragment;

public class ReviewsPagerAdapter extends FragmentPagerAdapter {

    public static final int REVIEWS_PAGE = 0;
    public static final int DETAILED_REVIEW_PAGE = 1;
    private static final int PAGES_COUNT = 2;
    private ReviewsFragment reviewsFragment;
    private ReviewFragment reviewFragment;

    public ReviewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setReviewsFragment(final ReviewsFragment reviewsFragment) {
        this.reviewsFragment = reviewsFragment;
    }

    public void setReviewFragment(ReviewFragment reviewFragment) {
        this.reviewFragment = reviewFragment;
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Override
    public Fragment getItem(int i) {
        return i == REVIEWS_PAGE ? reviewsFragment : reviewFragment;
    }
}
