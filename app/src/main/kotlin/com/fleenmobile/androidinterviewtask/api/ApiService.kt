package com.fleenmobile.androidinterviewtask.api

import com.fleenmobile.androidinterviewtask.data.PhotosResponse
import com.fleenmobile.androidinterviewtask.data.VenuesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("venues/explore")
    fun venues(
            @Query("near") location: String,
            @Query("client_id") clientId: String,
            @Query("client_secret") clientSecret: String,
            @Query("limit") limit: Int = 20,
            @Query("v") version: String = "20170801"
    ): Observable<VenuesResponse>

    @GET("venues/{venue_id}/photos")
    fun venuePhotos(
            @Path("venue_id") venueId: String,
            @Query("client_id") clientId: String,
            @Query("client_secret") clientSecret: String,
            @Query("v") version: String = "20170801"
    ): Observable<PhotosResponse>
}