package com.fleenmobile.androidinterviewtask.util.injection.module

import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelper
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelperImpl
import com.fleenmobile.androidinterviewtask.main.navigation.MainActivityRouter
import com.fleenmobile.androidinterviewtask.main.presentation.MainActivityPresenter
import com.fleenmobile.androidinterviewtask.main.ui.MainActivity
import com.fleenmobile.androidinterviewtask.util.injection.RuntimeScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View = activity

    @Provides
    fun router(activity: MainActivity): MainActivityContract.Router =
            MainActivityRouter(activity)

    @RuntimeScope
    @Provides
    fun presenter(view: MainActivityContract.View, router: MainActivityContract.Router): MainActivityContract.Presenter =
            MainActivityPresenter(view, router)

    @Provides
    fun eventHelper(presenter: MainActivityContract.Presenter): EventHelper =
            EventHelperImpl(presenter)
}