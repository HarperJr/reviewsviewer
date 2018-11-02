package com.harperjr.reviewsviewer.ui.reviews;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.model.MovieReview;
import com.harperjr.reviewsviewer.ui.reviews.content.ContentFragment;
import com.harperjr.reviewsviewer.ui.reviews.favorite.FavoritesFragment;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsPagerAdapter;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewFragment;
import com.harperjr.reviewsviewer.ui.reviews.content.fragment.ReviewsFragment;
import com.harperjr.reviewsviewer.ui.view.ReviewsViewPager;
import com.harperjr.reviewsviewer.ui.view.adapter.ReviewsRecyclerAdapter;

public class ReviewsActivity extends MvpAppCompatActivity implements ActivityView {

    private static final String CONTENT_IN_STACK = "ContentInStack";

    private FrameLayout mainHolder;

    private ContentFragment contentFragment;
    private FavoritesFragment favoritesFragment;

    private Toolbar toolbar;
    private MenuItem searchItem;
    private MenuItem likeItem;

    @InjectPresenter(type = PresenterType.GLOBAL)
    public ActivityPresenter activityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reviews);

        this.toolbar = findViewById(R.id.tool_bar);
        this.mainHolder = findViewById(R.id.main_holder);

        setSupportActionBar(toolbar);

        this.contentFragment = new ContentFragment();
        this.favoritesFragment = new FavoritesFragment();

        getSupportFragmentManager().beginTransaction()
        .replace(R.id.main_holder, contentFragment)
        .commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_holder, contentFragment)
                .commit();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        searchItem =  toolbar.getMenu().findItem(R.id.search);
        likeItem = toolbar.getMenu().findItem(R.id.like);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        final ActivityEventListener activityEventListener = contentFragment;

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
                activityEventListener.onSearching(s);
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
                    activityEventListener.onSearching(s);
                    searchView.clearFocus();
                }, COOL_DOWN);
                return true;
            }
        });

        likeItem.setOnMenuItemClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_holder, favoritesFragment)
                    .commit();
            return true;
        });

        return super.onCreateOptionsMenu(menu);
    }
}
