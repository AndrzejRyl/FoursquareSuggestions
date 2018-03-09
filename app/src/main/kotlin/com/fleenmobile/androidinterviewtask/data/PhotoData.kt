package com.fleenmobile.androidinterviewtask.data

import org.parceler.Parcel

@Parcel
data class PhotoItem(
        val prefix: String = "",
        val suffix: String = "",
        val width: Int = 500,
        val height: Int = 300
) {
    fun url(width: Int, height: Int) = "${prefix}${height}x${width}${suffix}"
}

@Parcel
data class PhotoData(val count: Int = 0, val items: List<PhotoItem> = listOf())