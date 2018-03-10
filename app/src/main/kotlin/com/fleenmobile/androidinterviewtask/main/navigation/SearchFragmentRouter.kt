package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToDetailsEvent
import org.greenrobot.eventbus.EventBus

class SearchFragmentRouter(
        private val eventBus: EventBus
) : SearchFragmentContract.Router {

    override fun navigateToDetails(venue: Venue) {
        eventBus.post(NavigateToDetailsEvent(venue))
    }
}