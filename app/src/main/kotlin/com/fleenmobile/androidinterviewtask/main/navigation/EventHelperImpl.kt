package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.navigation.events.MainNavigationEvent
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToDetailsEvent
import com.fleenmobile.androidinterviewtask.main.navigation.events.NavigateToSearchEvent

class EventHelperImpl(
        private val presenter: MainActivityContract.Presenter
) : EventHelper {

    override fun handleEvent(event: MainNavigationEvent) {
        when (event) {
            is NavigateToSearchEvent -> presenter.onNavigateToSearchEvent()
            is NavigateToDetailsEvent -> presenter.onNavigateToDetailsEvent(event.venue)
        }
    }
}