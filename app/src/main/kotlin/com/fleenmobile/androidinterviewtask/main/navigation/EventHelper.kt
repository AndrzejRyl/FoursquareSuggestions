package com.fleenmobile.androidinterviewtask.main.navigation

import com.fleenmobile.androidinterviewtask.main.navigation.events.MainNavigationEvent

interface EventHelper {

    fun handleEvent(event: MainNavigationEvent)
}
