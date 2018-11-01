package com.harperjr.reviewsviewer.localdb.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.harperjr.reviewsviewer.localdb.room.entity.base.EntityWithId;

@Entity(tableName = "Review")
public class ReviewEntity implements EntityWithId<Long> {

    @PrimaryKey
    private long id;

    private String displayTitle;

    private String headLine;

    private String byLine;

    private String summaryShort;

    private String publicationDate;

    private String linkUri;

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

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
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
