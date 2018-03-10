package com.fleenmobile.androidinterviewtask.main.ui

import android.support.v4.view.ViewPager
import android.widget.TextView
import butterknife.BindView
import com.fleenmobile.androidinterviewtask.BaseFragment
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract
import com.fleenmobile.androidinterviewtask.main.adapter.DetailsPhotosAdapter
import org.parceler.Parcels
import javax.inject.Inject

class DetailsFragment : BaseFragment<DetailsFragmentContract.Presenter>(), DetailsFragmentContract.View {

    companion object {
        const val TAG = "DetailsFragment"
    }

    @BindView(R.id.details_fragment_pager)
    lateinit var photosViewPager: ViewPager

    @BindView(R.id.details_fragment_name)
    lateinit var nameTextView: TextView

    @BindView(R.id.details_fragment_address)
    lateinit var locationTextView: TextView

    @BindView(R.id.details_fragment_opening_hours)
    lateinit var openingHoursTextView: TextView

    @BindView(R.id.details_fragment_people_count)
    lateinit var peopleCountTextView: TextView

    @Inject
    lateinit var photosAdapter: DetailsPhotosAdapter

    override val layoutId: Int = R.layout.fragment_details

    override fun initialize() {
        super.initialize()
        val venue = Parcels.unwrap<Venue>(arguments.getParcelable(MainActivity.VENUE_KEY))
        presenter.onVenue(venue)
    }

    override fun initView(venue: Venue){
        venue.apply {
            nameTextView.text = name
            locationTextView.text = location.getFullAddress()
            openingHoursTextView.text = hours.status
            peopleCountTextView.text = hereNow.summary

            photosViewPager.adapter = photosAdapter
            photosAdapter.updateData(photos)
        }
    }
}