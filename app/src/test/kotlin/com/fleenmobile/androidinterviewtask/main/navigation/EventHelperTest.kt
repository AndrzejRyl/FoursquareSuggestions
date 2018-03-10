package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.BaseTest
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToDetailsEvent
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToSearchEvent
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class EventHelperTest : BaseTest() {

    @Mock
    private lateinit var presenter: MainActivityContract.Presenter

    private lateinit var eventHelper: EventHelper

    private val venue: Venue = Venue()

    override fun setup() {
        super.setup()
        eventHelper = EventHelperImpl(presenter)
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(presenter)
    }

    @Test
    fun `should handle event navigation to search after handleEvent is called`() {
        eventHelper.handleEvent(NavigateToSearchEvent())

        verify(presenter, times(1)).onNavigateToSearchEvent()
    }

    @Test
    fun `should handle event navigation to details after handleEvent is called`() {
        eventHelper.handleEvent(NavigateToDetailsEvent(venue))

        verify(presenter, times(1)).onNavigateToDetailsEvent(venue)
    }
}