package com.harperjr.reviewsviewer.api.mapper;

import com.harperjr.reviewsviewer.api.model.Review;
import com.harperjr.reviewsviewer.model.MovieReview;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MultimediaMapper.class, LinkMapper.class})
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mappings({
            @Mapping(source = "displayTitle", target = "displayTitle"),
            @Mapping(source = "headline", target = "headline"),
            @Mapping(source = "byline", target = "byline"),
            @Mapping(source = "summaryShort", target = "summaryShort"),
            @Mapping(source = "publicationDate", target = "publicationDate"),
            @Mapping(source = "multimedia.source", target = "multimediaUri"),
            @Mapping(source = "link.url", target = "linkUri")
    })
    MovieReview entityToModel(Review review);

    List<MovieReview> entityListToModelList(List<Review> reviews);
}