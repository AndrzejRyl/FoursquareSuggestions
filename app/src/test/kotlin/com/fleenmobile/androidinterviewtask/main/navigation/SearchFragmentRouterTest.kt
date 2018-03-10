package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.BaseTest
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToDetailsEvent
import org.greenrobot.eventbus.EventBus
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class SearchFragmentRouterTest : BaseTest() {

    @Mock
    lateinit var eventBus: EventBus

    private val venue = Venue()

    private lateinit var router: SearchFragmentContract.Router

    override fun setup() {
        super.setup()
        router = SearchFragmentRouter(eventBus)
    }

    @Test
    fun `should send correct event on navigate to details`() {
        router.navigateToDetails(venue)

        verify(eventBus, times(1)).post(any(NavigateToDetailsEvent::class.java))
    }
}