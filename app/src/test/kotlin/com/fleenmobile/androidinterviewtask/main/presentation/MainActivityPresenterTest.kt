package com.fleenmobile.androidinterviewtask.main.presentation

import android.Manifest
import com.fleenmobile.androidinterviewtask.BaseTest
import com.fleenmobile.androidinterviewtask.data.Venue
import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.util.permissions.PermissionHandler
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class MainActivityPresenterTest : BaseTest() {

    @Mock
    lateinit var view: MainActivityContract.View

    @Mock
    lateinit var permissionHandler: PermissionHandler

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    @Mock
    lateinit var venue: Venue

    private lateinit var presenter: MainActivityContract.Presenter

    override fun setup() {
        super.setup()

        presenter = MainActivityPresenter(view, permissionHandler, compositeDisposable)
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(view, permissionHandler, compositeDisposable, venue)
    }

    @Test
    fun `should show initial fragment and request permissions on startup`() {
        `when`(permissionHandler.request(Manifest.permission.INTERNET))
                .thenReturn(Observable.just(true))

        presenter.initialize()

        commonInitializeVerification()
    }

    private fun commonInitializeVerification() {
        verify(view, times(1)).showSearchFragment()
        verify(compositeDisposable, times(1)).add(any())
        verify(permissionHandler, times(1)).request(Manifest.permission.INTERNET)
    }

    @Test
    fun `should show error in case permissions are not granted`() {
        `when`(permissionHandler.request(Manifest.permission.INTERNET))
                .thenReturn(Observable.just(false))

        presenter.initialize()

        commonInitializeVerification()
        verify(view, times(1)).showPermissionsError()
    }

    @Test
    fun `should clear composite disposable on clear`() {
        presenter.clear()

        verify(compositeDisposable, times(1)).clear()
    }

    @Test
    fun `should show search fragment on navigate to search event`() {
        presenter.onNavigateToSearchEvent()

        verify(view, times(1)).showSearchFragment()
    }

    @Test
    fun `should show details fragment on navigate to details event`() {
        presenter.onNavigateToDetailsEvent(venue)

        verify(view, times(1)).showDetailsFragment(venue)
    }

    @Test
    fun `should show search fragment on navigating back from details`() {
        presenter.onBackPressed(true)

        verify(view, times(1)).showSearchFragment()
    }

    @Test
    fun `should finish on navigating back from search`() {
        presenter.onBackPressed(false)

        verify(view, times(1)).finish()
    }
}