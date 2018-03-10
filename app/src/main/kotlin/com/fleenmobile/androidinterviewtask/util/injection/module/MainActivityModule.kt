package com.fleenmobile.androidinterviewtask.util.injection.module

import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelper
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelperImpl
import com.fleenmobile.androidinterviewtask.main.presentation.MainActivityPresenter
import com.fleenmobile.androidinterviewtask.main.ui.MainActivity
import com.fleenmobile.androidinterviewtask.util.injection.RuntimeScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View = activity

    @RuntimeScope
    @Provides
    fun presenter(view: MainActivityContract.View): MainActivityContract.Presenter =
            MainActivityPresenter(view)

    @Provides
    fun eventHelper(presenter: MainActivityContract.Presenter): EventHelper =
            EventHelperImpl(presenter)
}