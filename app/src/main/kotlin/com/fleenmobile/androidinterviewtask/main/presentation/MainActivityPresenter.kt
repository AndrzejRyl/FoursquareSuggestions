package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.MainActivityContract

class MainActivityPresenter(
        private val view: MainActivityContract.View
) : MainActivityContract.Presenter {

    override fun initialize() {
        view.showSearchFragment()
        // todo request permissions
    }

    override fun clear() {
        //todo
    }

    override fun onNavigateToSearchEvent() {
        view.showSearchFragment()
    }

    override fun onNavigateToDetailsEvent(venue: Venue) {
        view.showDetailsFragment(venue)
    }

    override fun onBackPressed(inDetailsFragment: Boolean) {
        when (inDetailsFragment) {
            true -> view.showSearchFragment()
            else -> view.finish()
        }
    }
}