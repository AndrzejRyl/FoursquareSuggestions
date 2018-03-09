package com.fleenmobile.androidinterviewtask.data

data class Venue(
        val id: String,
        val name: String,
        val location: VenueLocation,
        val hereNow : HereNowData,
        val photos: PhotoData
)