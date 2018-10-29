package com.harperjr.reviewsviewer.model;

import android.support.annotation.Nullable;

public class MovieReview {
    private final String title;

    private String byLine;
    private String headLine;
    private String summaryShort;
    private String publicationDate;

    private String linkUri;
    private String multimediaUri;

    public MovieReview(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getMultimediaUri() {
        return multimediaUri;
    }

    public void setMultimediaUri(@Nullable String multimediaUri) {
        this.multimediaUri = multimediaUri;
    }

    public String getLinkUri() {
        return linkUri;
    }

    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }
}
