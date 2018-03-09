package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import org.greenrobot.eventbus.EventBus

class SearchFragmentRouter(
        private val eventBus: EventBus
) : SearchFragmentContract.Router {

    override fun navigateToDetails(venue: Venue) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}