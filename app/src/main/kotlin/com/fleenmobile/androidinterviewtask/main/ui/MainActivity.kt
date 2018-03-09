package com.fleenmobile.androidinterviewtask.main.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.fleenmobile.androidinterviewtask.BaseActivity
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import org.parceler.Parcels

class MainActivity : BaseActivity<MainActivityContract.Presenter>(), MainActivityContract.View {

    companion object {
        const val VENUE_KEY = "venue"
    }

    override val layoutId: Int = R.layout.activity_main

    //region View
    override fun showSearchFragment() {
        showFragment(SearchFragment())
    }

    override fun showDetailsFragment(venue: Venue) {
        val fragment = DetailsFragment()
        fragment.arguments = Bundle()
        fragment.arguments.putParcelable(VENUE_KEY, Parcels.wrap(venue))
        showFragment(fragment)
    }
    //endregion

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_root, fragment)
                .commit()
    }
}
