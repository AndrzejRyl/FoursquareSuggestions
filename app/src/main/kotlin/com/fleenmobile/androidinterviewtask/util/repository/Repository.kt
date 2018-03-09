package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.data.PhotoData
import com.fleenmobile.androidinterviewtask.data.Venue
import io.reactivex.Observable

interface Repository {

    fun fetchVenues(location: String): Observable<Venue>

    fun fetchVenuePhotos(venue: Venue): Observable<Venue>
}