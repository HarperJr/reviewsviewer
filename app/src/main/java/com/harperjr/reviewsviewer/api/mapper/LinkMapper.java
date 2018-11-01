package com.harperjr.reviewsviewer.api.mapper;

import com.harperjr.reviewsviewer.api.model.Link;

public abstract class LinkMapper {

    public String entityToModel(Link link) {
        return link != null ? link.getUrl() : "";
    }
}
