package com.harperjr.reviewsviewer.api;

import com.harperjr.reviewsviewer.api.model.Result;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * set for getting results from determined item in result
     * @param offset
     * @param apiKey
     * @return
     */
    @GET("reviews/all.json")
    Call<Result> getReviews(
            @Query("offset") final int offset,
            @Query("api-key") final String apiKey
    );

    /**
     * set for getting results from determined item in result
     * @param offset
     * searching query, request works the way as getReviews do if null
     * @param query
     * @param apiKey
     * @return
     */
    @GET("reviews/search.json")
    Call<Result> searchReviews(
            @Query("offset") final int offset,
            @Query("query") final String query,
            @Query("api-key") final String apiKey
    );
}
