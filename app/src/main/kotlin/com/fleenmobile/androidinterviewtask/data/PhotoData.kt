package com.fleenmobile.androidinterviewtask.data

data class PhotoItem(val prefix: String, val suffix: String)

data class PhotoData(val count: Int, val items: List<PhotoItem>)