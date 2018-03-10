package com.fleenmobile.androidinterviewtask.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.Venue

typealias OnVenueClickedListener = (venue: Venue) -> Unit

class VenuesAdapter(val data: ArrayList<Venue>) : RecyclerView.Adapter<VenuesViewHolder>() {

    var onVenueClicked: OnVenueClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenuesViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.venue_item_view, parent, false)
        return VenuesViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VenuesViewHolder, position: Int) {
        val venue = data[position]

        holder.apply {
            setVenue(venue)
            onClickListener = { onVenueClicked?.invoke(it) }
        }
    }

    fun addItem(venue: Venue) {
        data.add(venue)
        notifyDataSetChanged()
    }

    fun clearItems() {
        data.clear()
        notifyDataSetChanged()
    }
}