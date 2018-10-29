package com.harperjr.reviewsviewer.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class ReviewsRecyclerView extends RecyclerView {

    public interface OnScrolledListener {
        void onScrolled();
    }

    private static final int LEAST_TILL_LOAD = 3;

    private boolean isLoading;
    private OnScrolledListener onScrolledListener;

    public void setOnScrolledListener(OnScrolledListener onScrolledListener) {
        this.onScrolledListener = onScrolledListener;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public ReviewsRecyclerView(@NonNull Context context) {
        super(context);
    }

    public ReviewsRecyclerView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ReviewsRecyclerView(@NonNull Context context, @NonNull AttributeSet attributeSet, int style) {
        super(context, attributeSet, style);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        final LinearLayoutManager layoutManager = (LinearLayoutManager) this.getLayoutManager();

        final int visibleCount = layoutManager.getChildCount();
        final int totalCount = layoutManager.getItemCount();
        final int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();

        if ((visibleCount + firstVisibleItemPos + LEAST_TILL_LOAD) >= totalCount) {
            if (onScrolledListener != null && !isLoading) {
                onScrolledListener.onScrolled();
            }
        }
    }
}
