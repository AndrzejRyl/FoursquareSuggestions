package com.fleenmobile.androidinterviewtask.util.injection.module

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.fleenmobile.androidinterviewtask.main.SearchFragmentContract
import com.fleenmobile.androidinterviewtask.main.adapter.VenuesAdapter
import com.fleenmobile.androidinterviewtask.main.navigation.SearchFragmentRouter
import com.fleenmobile.androidinterviewtask.main.presentation.SearchFragmentPresenter
import com.fleenmobile.androidinterviewtask.main.ui.SearchFragment
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus

@Module
class SearchFragmentModule {

    @Provides
    fun view(searchFragment: SearchFragment): SearchFragmentContract.View = searchFragment

    @Provides
    fun router(eventBus: EventBus): SearchFragmentContract.Router = SearchFragmentRouter(eventBus)

    @Provides
    fun presenter(
            view: SearchFragmentContract.View,
            router: SearchFragmentContract.Router,
            compositeDisposable: CompositeDisposable,
            repository: Repository
    ): SearchFragmentContract.Presenter =
            SearchFragmentPresenter(view, router, compositeDisposable, repository)

    @Provides
    fun adapter(): VenuesAdapter = VenuesAdapter(arrayListOf())

    @Provides
    fun layoutManager(context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context)
}