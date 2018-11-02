package com.harperjr.reviewsviewer.localdb;


import com.harperjr.reviewsviewer.localdb.room.mapper.ReviewMapper;
import com.harperjr.reviewsviewer.localdb.room.mapper.ReviewMapperImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class MapperModule {

    @Provides
    @Reusable
    public ReviewMapper provideReviewMapper() {
        return new ReviewMapperImpl();
    }
}
