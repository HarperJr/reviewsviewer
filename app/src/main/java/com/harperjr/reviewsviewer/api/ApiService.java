package com.harperjr.reviewsviewer.api;

import com.harperjr.reviewsviewer.api.nyt.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("reviews/all.json")
    Call<Result> getReviews(
            @Query("offset") final int offset,
            @Query("api-key") final String apiKey
    );

    @GET("reviews/search.json")
    Call<Result> searchReviews(
            @Query("offset") final int offset,
            @Query("query") final String query,
            @Query("api-key") final String apiKey
    );
}
