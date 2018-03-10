package com.fleenmobile.androidinterviewtask.main.presentation

import android.Manifest
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.util.permissions.PermissionHandler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class MainActivityPresenter(
        private val view: MainActivityContract.View,
        private val permissionHandler: PermissionHandler,
        private val compositeDisposable: CompositeDisposable
) : MainActivityContract.Presenter {

    override fun initialize() {
        view.showSearchFragment()
        requestPermissions()
    }

    private fun requestPermissions() {
        compositeDisposable.add(
                permissionHandler
                        .request(Manifest.permission.INTERNET)
                        .subscribe(
                                { granted ->
                                    if (!granted) {
                                        view.showPermissionsError()
                                    }
                                },
                                {
                                    Timber.e(it)
                                    view.showPermissionsError()
                                }
                        )
        )
    }

    override fun clear() {
        compositeDisposable.clear()
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