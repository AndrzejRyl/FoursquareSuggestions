package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract

class DetailsFragmentPresenter(
        private val view: DetailsFragmentContract.View
) : DetailsFragmentContract.Presenter {

    override fun onVenue(venue: Venue) {
        view.initView(venue)
    }

    override fun initialize() {}

    override fun clear() {}
}