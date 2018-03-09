package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToSearchEvent
import org.greenrobot.eventbus.EventBus

class DetailsFragmentRouter(
        private val eventBus: EventBus
) : DetailsFragmentContract.Router {

    override fun navigateToSearch() {
        eventBus.post(NavigateToSearchEvent())
    }
}