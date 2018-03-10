package com.fleenmobile.androidinterviewtask.util.injection.module

import com.fleenmobile.androidinterviewtask.main.MainActivityContract
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelper
import com.fleenmobile.androidinterviewtask.main.navigation.EventHelperImpl
import com.fleenmobile.androidinterviewtask.main.presentation.MainActivityPresenter
import com.fleenmobile.androidinterviewtask.main.ui.MainActivity
import com.fleenmobile.androidinterviewtask.util.injection.RuntimeScope
import com.fleenmobile.androidinterviewtask.util.permissions.PermissionHandler
import com.fleenmobile.androidinterviewtask.util.permissions.PermissionHandlerImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View = activity

    @RuntimeScope
    @Provides
    fun presenter(
            view: MainActivityContract.View,
            permissionHandler: PermissionHandler,
            compositeDisposable: CompositeDisposable
    ): MainActivityContract.Presenter =
            MainActivityPresenter(view, permissionHandler, compositeDisposable)

    @Provides
    fun eventHelper(presenter: MainActivityContract.Presenter): EventHelper =
            EventHelperImpl(presenter)

    @Provides
    fun permissionHandlerSubject(): PublishSubject<Boolean> = PublishSubject.create()

    @Provides
    fun permissionHandler(activity: MainActivity, publishSubject: PublishSubject<Boolean>):
            PermissionHandler = PermissionHandlerImpl(activity, publishSubject)
}