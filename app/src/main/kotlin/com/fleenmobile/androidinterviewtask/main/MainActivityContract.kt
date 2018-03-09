package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Venue

interface MainActivityContract {

    interface View {
        fun showSearchFragment()
        fun showDetailsFragment(venue: Venue)
    }

    interface Router
    interface Presenter : BaseContract.Presenter
}