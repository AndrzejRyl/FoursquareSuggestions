package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class SearchFragmentPresenter(
        private val view: SearchFragmentContract.View,
        private val router: SearchFragmentContract.Router,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : SearchFragmentContract.Presenter {

    override fun initialize() {
        compositeDisposable.add(
                view
                        .getSearchTextWatcher()
                        .doOnNext { view.showProgress() }
                        .subscribe(
                                { fetchVenues(it) },
                                {
                                    Timber.e(it)
                                    view.hideProgress()
                                }
                        )
        )
    }

    private fun fetchVenues(searchQuery: String) {
        compositeDisposable.add(
                repository
                        .fetchVenues(searchQuery)
                        .doOnTerminate { view.hideProgress() }
                        .subscribe(
                                {
                                    view.updateVenuesList(it)
                                },
                                { Timber.e(it) }
                        )
        )
    }

    override fun venueChosen(venue: Venue) {
        router.navigateToDetails(venue)
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}