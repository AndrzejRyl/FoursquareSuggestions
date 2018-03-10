package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract
import com.fleenmobile.androidinterviewtask.data.Venue
import io.reactivex.Observable

interface SearchFragmentContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun getSearchTextWatcher(): Observable<String>
        fun addVenue(venue: Venue)
        fun clearVenues()
        fun hideKeyboard()
        fun showKeyboard()
        fun focusOnSearch()
    }

    interface Router {
        fun navigateToDetails(venue: Venue)
    }

    interface Presenter : BaseContract.Presenter {
        fun venueChosen(venue: Venue)
    }
}