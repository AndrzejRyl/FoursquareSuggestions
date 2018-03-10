package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Venue

interface MainActivityContract {

    interface View {
        fun showSearchFragment()
        fun showDetailsFragment(venue: Venue)
        fun finish()
        fun showPermissionsError()
    }

    interface Presenter : BaseContract.Presenter {
        fun onNavigateToSearchEvent()
        fun onNavigateToDetailsEvent(venue: Venue)
        fun onBackPressed(inDetailsFragment: Boolean)
    }
}