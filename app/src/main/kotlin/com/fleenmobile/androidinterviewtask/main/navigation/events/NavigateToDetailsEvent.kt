package com.fleenmobile.androidinterviewtask.main.navigation.events

import com.fleenmobile.androidinterviewtask.data.Venue

data class NavigateToDetailsEvent(val venue: Venue) : MainNavigationEvent()