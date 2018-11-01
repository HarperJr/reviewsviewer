package com.harperjr.reviewsviewer.localdb;

import com.harperjr.reviewsviewer.localdb.repository.ReviewRepository;
import com.harperjr.reviewsviewer.localdb.room.repository.ReviewRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    abstract ReviewRepository reviewRepository(ReviewRepositoryImpl reviewRepository);
}
