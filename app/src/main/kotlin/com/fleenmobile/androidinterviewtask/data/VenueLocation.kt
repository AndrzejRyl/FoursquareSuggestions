package com.fleenmobile.androidinterviewtask.data

import org.parceler.Parcel

@Parcel
data class VenueLocation(private val formattedAddress: List<String> = listOf()) {
    fun getFullAddress(): String = formattedAddress.joinToString(" ")
}