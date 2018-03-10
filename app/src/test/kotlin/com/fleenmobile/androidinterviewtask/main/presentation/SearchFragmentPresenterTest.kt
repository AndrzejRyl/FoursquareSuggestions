package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.BaseTest
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class SearchFragmentPresenterTest : BaseTest() {

    @Mock
    lateinit var view: SearchFragmentContract.View

    @Mock
    lateinit var router: SearchFragmentContract.Router

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var venue: Venue

    private val mockString = "mock"
    private lateinit var presenter: SearchFragmentContract.Presenter

    override fun setup() {
        super.setup()

        trampolineRxPlugin()

        presenter = SearchFragmentPresenter(
                view,
                router,
                compositeDisposable,
                repository
        )
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(view, router, compositeDisposable, repository, venue)
    }

    @Test
    fun `should prepare view on startup`() {
        `when`(view.getSearchTextWatcher()).thenReturn(Observable.just(""))

        presenter.initialize()

        commonInitializeVerification()
        verify(compositeDisposable, times(1)).add(any())
    }

    private fun commonInitializeVerification() {
        verify(view, times(1)).showKeyboard()
        verify(view, times(1)).focusOnSearch()
        verify(view, times(1)).getSearchTextWatcher()
    }

    @Test
    fun `should fetch venues on new search query`() {
        `when`(view.getSearchTextWatcher()).thenReturn(Observable.just(mockString))
        `when`(repository.fetchVenues(mockString)).thenReturn(Observable.just(venue))
        `when`(repository.fetchVenuePhotos(venue)).thenReturn(Observable.just(venue))

        presenter.initialize()

        commonInitializeVerification()
        commonFetchVenuesVerification()
        verify(repository, times(1)).fetchVenuePhotos(venue)
        verify(view, times(1)).addVenue(venue)
    }

    private fun commonFetchVenuesVerification() {
        verify(view, times(1)).clearVenues()
        verify(view, times(1)).showProgress()
        verify(compositeDisposable, times(2)).add(any())
        verify(repository, times(1)).fetchVenues(mockString)
        verify(view, times(1)).hideProgress()
    }

    @Test
    fun `should hide progress bar on not fetching venues`() {
        `when`(view.getSearchTextWatcher()).thenReturn(Observable.just(mockString))
        `when`(repository.fetchVenues(mockString)).thenReturn(Observable.error(IllegalStateException()))

        presenter.initialize()

        commonInitializeVerification()
        commonFetchVenuesVerification()
    }

    @Test
    fun `should not add venues on fetching error`() {
        `when`(view.getSearchTextWatcher()).thenReturn(Observable.just(mockString))
        `when`(repository.fetchVenues(mockString)).thenReturn(Observable.error(IllegalStateException()))

        presenter.initialize()

        commonInitializeVerification()
        commonFetchVenuesVerification()
        verify(view, never()).addVenue(venue)
    }

    @Test
    fun `should not add venues on fetching photos error`() {
        `when`(view.getSearchTextWatcher()).thenReturn(Observable.just(mockString))
        `when`(repository.fetchVenues(mockString)).thenReturn(Observable.just(venue))
        `when`(repository.fetchVenuePhotos(venue)).thenReturn(Observable.error(IllegalStateException()))

        presenter.initialize()

        commonInitializeVerification()
        commonFetchVenuesVerification()
        verify(repository, times(1)).fetchVenuePhotos(venue)
        verify(view, never()).addVenue(venue)
    }

    @Test
    fun `should navigate to details on choosing venue`() {
        presenter.venueChosen(venue)

        verify(router, times(1)).navigateToDetails(venue)
        verify(view, times(1)).hideKeyboard()
    }

    @Test
    fun `should clear composite disposable on clear`() {
        presenter.clear()

        verify(compositeDisposable, times(1)).clear()
    }
}