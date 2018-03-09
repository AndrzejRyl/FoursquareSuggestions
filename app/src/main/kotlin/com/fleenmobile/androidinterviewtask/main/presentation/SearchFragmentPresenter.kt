package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
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
        view.clearVenues()
        compositeDisposable.add(
                repository
                        .fetchVenues(searchQuery)
                        .flatMap { repository.fetchVenuePhotos(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate { view.hideProgress() }
                        .subscribe(
                                {
                                    view.addVenue(it)
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