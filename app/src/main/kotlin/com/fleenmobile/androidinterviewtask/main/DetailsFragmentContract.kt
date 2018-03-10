package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Venue

interface DetailsFragmentContract {

    interface View {
        fun initView(venue: Venue)
    }

    interface Presenter : BaseContract.Presenter {
        fun onVenue(venue: Venue)
    }
}