package com.harperjr.reviewsviewer.api.mapper;

import com.harperjr.reviewsviewer.api.model.Multimedia;

import org.mapstruct.Mapper;

@Mapper
public abstract class MultimediaMapper {

    public String entityToModel(Multimedia entity) {
        return entity != null ? entity.getSource() : "";
    }
}
