package com.fleenmobile.androidinterviewtask.main.ui

import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ProgressBar
import butterknife.BindView
import com.fleenmobile.androidinterviewtask.BaseFragment
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.hide
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.main.adapter.VenuesAdapter
import com.fleenmobile.androidinterviewtask.show
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchFragmentContract.Presenter>(),
        SearchFragmentContract.View {

    companion object {
        const val TAG = "SearchFragment"
        const val EDIT_TEXT_TIMEOUT = 600L
    }

    @BindView(R.id.search_fragment_search)
    lateinit var searchEditText: EditText

    @BindView(R.id.search_fragment_progress)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.search_fragment_venues_list)
    lateinit var venuesRecyclerView: RecyclerView

    @Inject
    lateinit var venuesAdapter: VenuesAdapter

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    override val layoutId: Int = R.layout.fragment_search

    override fun initialize() {
        super.initialize()
        venuesRecyclerView.layoutManager = layoutManager
        venuesAdapter.onVenueClicked = { presenter.venueChosen(it) }
        venuesRecyclerView.adapter = venuesAdapter
    }

    //region View
    override fun getSearchTextWatcher(): Observable<String> =
            RxTextView
                    .textChanges(searchEditText)
                    .map { it.toString() }
                    .debounce(EDIT_TEXT_TIMEOUT, TimeUnit.MILLISECONDS)

    override fun addVenue(venue: Venue) {
        venuesAdapter.addItem(venue)
    }

    override fun clearVenues() {
        venuesAdapter.clearItems()
    }

    override fun showProgress() {
        venuesRecyclerView.hide()
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
        venuesRecyclerView.show()
    }
    //endregion
}