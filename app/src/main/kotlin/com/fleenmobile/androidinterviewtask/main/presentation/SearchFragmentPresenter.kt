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
        view.showKeyboard()
        view.focusOnSearch()

        compositeDisposable.add(
                view
                        .getSearchTextWatcher()
                        .filter { !it.isEmpty() }
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
        compositeDisposable.add(
                repository
                        .fetchVenues(searchQuery)
                        .doOnSubscribe { view.clearVenues() }
                        .switchMap { repository.fetchVenuePhotos(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate { view.hideProgress() }
                        .subscribe(
                                { view.addVenue(it) },
                                { Timber.e(it) }
                        )
        )
    }

    override fun venueChosen(venue: Venue) {
        view.hideKeyboard()
        router.navigateToDetails(venue)
    }

    override fun clear() {
        compositeDisposable.clear()
    }
}