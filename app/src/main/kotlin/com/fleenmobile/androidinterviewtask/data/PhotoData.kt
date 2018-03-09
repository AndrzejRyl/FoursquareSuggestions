package com.fleenmobile.androidinterviewtask.data

import org.parceler.Parcel

@Parcel
data class PhotoItem(val prefix: String = "", val suffix: String = "")

@Parcel
data class PhotoData(val count: Int = 0, val items: List<PhotoItem> = listOf())