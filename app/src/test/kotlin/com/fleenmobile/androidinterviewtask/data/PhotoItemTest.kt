package com.fleenmobile.androidinterviewtask.data

import com.fleenmobile.androidinterviewtask.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class PhotoItemTest : BaseTest() {

    private val mockString = "mock"

    private lateinit var photoItem: PhotoItem
    private var mockWidth: Int = 50
    private var mockHeight: Int = 50
    private var mockItemSize: Int = 50

    override fun setup() {
        super.setup()

        mockWidth = Random().nextInt()
        mockHeight = Random().nextInt()
        mockItemSize = Random().nextInt()

        photoItem = PhotoItem(
                mockString,
                mockString,
                mockWidth,
                mockHeight
        )
    }

    @Test
    fun `should return correct url`() {
        val url = photoItem.url(mockItemSize)

        val itemHeight = mockItemSize * (mockWidth.toFloat() / mockHeight)

        assertEquals("${mockString}${itemHeight.toInt()}x${mockItemSize}${mockString}", url)
    }
}