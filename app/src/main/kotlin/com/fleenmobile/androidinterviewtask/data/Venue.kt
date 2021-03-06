package com.fleenmobile.androidinterviewtask.data

import org.parceler.Parcel

@Parcel
data class Venue(
        val id: String = "",
        val name: String = "",
        val location: VenueLocation = VenueLocation(),
        val hereNow: HereNowData = HereNowData(),
        val hours: HoursData = HoursData(),
        var photos: PhotoData? = PhotoData()
)