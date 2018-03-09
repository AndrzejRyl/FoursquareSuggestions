package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.BuildConfig
import com.fleenmobile.androidinterviewtask.api.ApiService
import com.fleenmobile.androidinterviewtask.data.Venue
import io.reactivex.Observable

class RepositoryImpl(
        private val apiService: ApiService
) : Repository {

    override fun fetchVenues(location: String): Observable<Venue> =
            apiService
                    .venues(
                            location,
                            BuildConfig.FOURSQUARE_CLIENT_ID,
                            BuildConfig.FOURSQUARE_CLIENT_SECRET
                    )
                    .map { it.getVenues() }
                    .flatMapIterable { it }

    override fun fetchVenuePhotos(venue: Venue): Observable<Venue> =
            apiService
                    .venuePhotos(venue.id,
                            BuildConfig.FOURSQUARE_CLIENT_ID,
                            BuildConfig.FOURSQUARE_CLIENT_SECRET
                    )
                    .map {
                        venue.photos = it.response.photos
                        return@map venue
                    }
}