package com.fleenmobile.androidinterviewtask.data

import com.fleenmobile.androidinterviewtask.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test

class VenueLocationTest : BaseTest() {

    private val mockString = "mock"
    private val mockList = arrayListOf(mockString, mockString, mockString)
    private lateinit var venueLocation: VenueLocation

    override fun setup() {
        super.setup()

        venueLocation = VenueLocation(mockList)
    }

    @Test
    fun `should return correctly formatted address`() {
        val address = venueLocation.getFullAddress()

        assertEquals("mock mock mock", address)
    }
}