package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.BaseTest
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class DetailsFragmentPresenterTest : BaseTest() {

    @Mock
    lateinit var view: DetailsFragmentContract.View

    @Mock
    lateinit var venue: Venue

    private lateinit var presenter: DetailsFragmentContract.Presenter

    override fun setup() {
        super.setup()

        presenter = DetailsFragmentPresenter(view)
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(view, venue)
    }

    @Test
    fun `should initialize view on startup`() {
        presenter.onVenue(venue)

        verify(view, times(1)).initView(venue)
    }
}