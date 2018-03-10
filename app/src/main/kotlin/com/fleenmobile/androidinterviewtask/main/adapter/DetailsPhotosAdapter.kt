package com.fleenmobile.androidinterviewtask.main.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.data.PhotoData
import com.fleenmobile.androidinterviewtask.data.PhotoItem
import com.squareup.picasso.Picasso

class DetailsPhotosAdapter(
        private val context: Context,
        private val photos: ArrayList<PhotoItem>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = (view == `object`)

    override fun getCount(): Int = photos.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.details_image_view, container, false)

        val url = photos[position].url(container.width)
        val imageView = layout.findViewById<ImageView>(R.id.slider_item_image)

        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    fun updateData(photoData: PhotoData?) {
        photoData?.let {
            photos.clear()
            photos.addAll(it.items)
            notifyDataSetChanged()
        }
    }
}