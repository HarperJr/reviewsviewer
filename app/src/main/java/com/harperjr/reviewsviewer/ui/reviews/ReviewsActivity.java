package com.harperjr.reviewsviewer.ui.reviews;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.ui.reviews.mvp.ActivityPresenter;
import com.harperjr.reviewsviewer.ui.reviews.mvp.ActivityView;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsPagerAdapter;
import com.harperjr.reviewsviewer.ui.reviews.fragment.ActivityEventListener;
import com.harperjr.reviewsviewer.ui.reviews.fragment.DetailedReviewFragment;
import com.harperjr.reviewsviewer.ui.reviews.fragment.ReviewsFragment;
import com.harperjr.reviewsviewer.ui.view.ReviewsViewPager;

public class ReviewsActivity extends MvpAppCompatActivity implements ActivityView {

    private ReviewsFragment reviewsFragment;
    private DetailedReviewFragment detailedReviewFragment;

    private Toolbar toolbar;
    private MenuItem searchItem;

    private ReviewsViewPager viewPager;

    private ReviewsPagerAdapter reviewsPagerAdapter;

    @InjectPresenter(type = PresenterType.GLOBAL)
    public ActivityPresenter activityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reviews);

        this.reviewsFragment = new ReviewsFragment();
        this.reviewsFragment.setInteractionListener(review -> activityPresenter.navigateToDetailed(review));
        this.detailedReviewFragment = new DetailedReviewFragment();
        this.reviewsPagerAdapter = new ReviewsPagerAdapter(getSupportFragmentManager());
        this.reviewsPagerAdapter.setReviewsFragment(reviewsFragment);
        this.reviewsPagerAdapter.setDetailedReviewFragment(detailedReviewFragment);
        this.toolbar = findViewById(R.id.tool_bar);
        this.viewPager = findViewById(R.id.pager);
        this.viewPager.setAdapter(reviewsPagerAdapter);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int page, float v, int i1) {
            }

            @Override
            public void onPageSelected(int page) {
                final boolean focusedOnDetailedPage = page == ReviewsPagerAdapter.DETAILED_REVIEW_PAGE;
                if (focusedOnDetailedPage) {
                    ReviewsActivity.this.viewPager.setScrollable(true);
                } else {
                    toolbar.setTitle(R.string.app_name);
                    searchItem.setVisible(true);
                    ReviewsActivity.this.viewPager.setScrollable(false);
                }

            }

            @Override
            public void onPageScrollStateChanged(int page) {
            }
        });

        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        searchItem =  toolbar.getMenu().findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        final ActivityEventListener activityEventListener = reviewsFragment;

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                activityEventListener.onSearching(null);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Handler typingHandler = new Handler();
            private int COOL_DOWN = 1500;
            @Override
            public boolean onQueryTextSubmit(String s) {
                typingHandler.removeCallbacks(null);

                reviewsFragment.onSearching(s);
                searchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                typingHandler.removeCallbacksAndMessages(null);
                if (s.equals("")) {
                    return false;
                }
                typingHandler.postDelayed(() -> {
                    reviewsFragment.onSearching(s);
                    searchView.clearFocus();
                }, COOL_DOWN);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void navigateToDetailed(@NonNull final MovieReview review) {
        try {
            this.detailedReviewFragment.load(review);
            this.toolbar.setTitle(review.getTitle());

            this.searchItem.setVisible(false);
            this.searchItem.collapseActionView();
            this.viewPager.setCurrentItem(1, true);
        } catch (NullPointerException ex) {
            //lazy
        }
    }
}
