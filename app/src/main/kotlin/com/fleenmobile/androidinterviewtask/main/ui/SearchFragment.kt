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
import com.fleenmobile.androidinterviewtask.show
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment<SearchFragmentContract.Presenter>(),
        SearchFragmentContract.View {

    companion object {
        const val EDIT_TEXT_TIMEOUT = 300L
    }

    @BindView(R.id.search_fragment_search)
    lateinit var searchEditText: EditText

    @BindView(R.id.search_fragment_progress)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.search_fragment_venues_list)
    lateinit var venuesRecyclerView: RecyclerView

    override val layoutId: Int = R.layout.fragment_search

    //region View
    override fun getSearchTextWatcher(): Observable<String> =
            RxTextView
                    .textChanges(searchEditText)
                    .map { toString() }
                    .debounce(EDIT_TEXT_TIMEOUT, TimeUnit.MILLISECONDS)

    override fun updateVenuesList(list: List<Venue>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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