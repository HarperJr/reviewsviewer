package com.harperjr.reviewsviewer.ui.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.harperjr.reviewsviewer.R;
import com.harperjr.reviewsviewer.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsRecyclerAdapter.ViewHolder> {

    public interface InteractionListener {
        void onReadMoreButtonClicked(@NonNull final MovieReview movieReview);
    }

    private List<MovieReview> movieReviews = new ArrayList<>();
    private InteractionListener interactionListener;

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View viewItem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.review_holder_item, viewGroup, false);
        return new ViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(movieReviews.get(i));
    }

    @Override
    public int getItemCount() {
        return movieReviews.size();
    }

    public void clearReviews() {
        this.movieReviews.clear();
        this.notifyDataSetChanged();
    }

    public void addReviews(@NonNull List<MovieReview> movieReviews) {
        if (this.movieReviews != null) {
            this.movieReviews.addAll(movieReviews);
        }
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView headLine;
        private final TextView byLine;
        private final TextView summaryShort;
        private final TextView publicationDate;
        private final MaterialButton readMoreButton;
        private ImageView imageView;
        private String imageUri;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            final ConstraintLayout contentHolder = itemView.findViewById(R.id.content_holder);
            final ConstraintLayout textHolder = contentHolder.findViewById(R.id.text_holder);
            this.title = contentHolder.findViewById(R.id.review_title);
            this.imageView = contentHolder.findViewById(R.id.multimedia);
            this.readMoreButton = contentHolder.findViewById(R.id.read_more_button);
            this.headLine = textHolder.findViewById(R.id.headline);
            this.byLine = textHolder.findViewById(R.id.byline);
            this.summaryShort = textHolder.findViewById(R.id.summary_short);
            this.publicationDate = itemView.findViewById(R.id.publication_date);
        }

        private void bind(final MovieReview movieReview) {

            this.title.setText(movieReview.getDisplayTitle());
            this.headLine.setText(movieReview.getHeadline());
            this.byLine.setText(movieReview.getByline());
            this.summaryShort.setText(movieReview.getSummaryShort());
            this.publicationDate.setText(movieReview.getPublicationDate());
            this.imageUri = movieReview.getMultimediaUri();

            if (imageView.getMeasuredWidth() == 0) {
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                this.imageView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            } else {
                onImageViewSizeDetermined();
            }

            if (ReviewsRecyclerAdapter.this.interactionListener != null) {
                readMoreButton.setOnClickListener(view ->
                        ReviewsRecyclerAdapter.this.interactionListener.onReadMoreButtonClicked(movieReview));
            }
        }

        private void onImageViewSizeDetermined() {
            if (imageUri != null) {
                Glide.with(imageView)
                        .load(imageUri)
                        .apply(new RequestOptions()
                                .error(R.drawable.ic_multimedia_not_loaded)
                                .placeholder(R.drawable.ic_multimedia_placeholder)
                                .override(imageView.getMeasuredWidth())
                                .fitCenter())
                        .into(imageView);
            } else {
                Glide.with(imageView)
                        .load(R.drawable.ic_multimedia_not_loaded)
                        .into(imageView);
            }
        }

        private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (imageView.getMeasuredWidth() > 0) {
                    onImageViewSizeDetermined();
                    imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        };
    }
}
