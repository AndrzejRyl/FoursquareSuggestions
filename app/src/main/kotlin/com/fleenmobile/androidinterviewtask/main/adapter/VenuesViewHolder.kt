package com.fleenmobile.androidinterviewtask.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Venue
import com.squareup.picasso.Picasso

class VenuesViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.venue_item_image)
    lateinit var imageView: ImageView

    @BindView(R.id.venue_item_name)
    lateinit var nameTextView: TextView

    var onClickListener: OnVenueClickedListener? = null

    private lateinit var venue: Venue

    init {
        ButterKnife.bind(this, view)
    }

    fun setVenue(venue: Venue) {
        this.venue = venue

        nameTextView.text = venue.name

        val firstPhoto = venue.photos?.items?.firstOrNull()

        firstPhoto?.let {
            val url = it.url(view.width)

            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(imageView)
        }
    }

    @OnClick(R.id.venue_item_root)
    fun onItemClicked() {
        onClickListener?.invoke(venue)
    }
}