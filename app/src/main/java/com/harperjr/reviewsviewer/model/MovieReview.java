package com.harperjr.reviewsviewer.model;

import com.harperjr.reviewsviewer.model.base.ModelWithId;

public class MovieReview implements ModelWithId<Long> {
    /**
     * Id записи
     */
    private long id;
    /**
     * Тайтл ревьюшки
     */
    private String displayTitle;
    /**
     *
     */
    private String headline;
    /**
     *
     */
    private String byline;
    /**
     *
     */
    private String summaryShort;
    /**
     *
     */
    private String publicationDate;
    /**
     *
     */
    private String linkUri;
    /**
     *
     */
    private String multimediaUri;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
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

    public String getLinkUri() {
        return linkUri;
    }

    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }

    public String getMultimediaUri() {
        return multimediaUri;
    }

    public void setMultimediaUri(String multimediaUri) {
        this.multimediaUri = multimediaUri;
    }
}
