package com.fleenmobile.androidinterviewtask.data

import org.parceler.Parcel

@Parcel
data class PhotoItem(
        val prefix: String = "",
        val suffix: String = "",
        val width: Int = 500,
        val height: Int = 300
) {
    fun url(viewWidth: Int): String {
        val maxWidth = width.toFloat()
        val maxHeight = height.toFloat()
        val itemHeight = viewWidth * (maxWidth / maxHeight)

        return "${prefix}${itemHeight.toInt()}x${viewWidth}${suffix}"
    }
}

@Parcel
data class PhotoData(val count: Int = 0, val items: List<PhotoItem> = listOf())