package com.fleenmobile.androidinterviewtask.main.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.fleenmobile.androidinterviewtask.BaseActivity
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelper
import com.fleenmobile.androidinterviewtask.main.navigation.events.MainNavigationEvent
import com.fleenmobile.androidinterviewtask.showToast
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.parceler.Parcels
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityContract.Presenter>(true),
        MainActivityContract.View {

    companion object {
        const val VENUE_KEY = "venue"
    }

    @Inject
    lateinit var eventHelper: EventHelper

    override val layoutId: Int = R.layout.activity_main

    //region View
    override fun showSearchFragment() {
        showFragment(SearchFragment(), SearchFragment.TAG)
    }

    override fun showDetailsFragment(venue: Venue) {
        val fragment = DetailsFragment()
        fragment.arguments = Bundle()
        fragment.arguments.putParcelable(VENUE_KEY, Parcels.wrap(venue))
        showFragment(fragment, DetailsFragment.TAG)
    }

    override fun showPermissionsError() {
        showToast(resources.getString(R.string.permissions_error), Toast.LENGTH_LONG)
    }

    //endregion

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_root, fragment, tag)
                .commit()
    }

    override fun onBackPressed() {
        val detailsFragment = supportFragmentManager.findFragmentByTag(DetailsFragment.TAG)
        presenter.onBackPressed(inDetailsFragment = detailsFragment != null)
    }

    //region EventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun navigationEvent(event: MainNavigationEvent) {
        eventHelper.handleEvent(event)
    }
    //endregion
}
