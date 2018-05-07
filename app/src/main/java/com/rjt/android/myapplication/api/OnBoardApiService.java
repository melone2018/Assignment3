package com.rjt.android.myapplication.api;

import com.rjt.android.myapplication.model.pojo.BoundaryResponse;
import com.rjt.android.myapplication.model.pojo.BoundaryResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface OnBoardApiService {

    @Headers({
            "Accept:application/json",
            "APIKey:57191c1300a60167f2ed1bef50c5f9f4"})
    @GET("hierarchy/lookup")
    io.reactivex.Observable<BoundaryResponse> getArea(@Query("latitude") double latitude,
                                                      @Query("longitude") double longitude);
}
