package com.fleenmobile.androidinterviewtask.main.presentation

import com.fleenmobile.androidinterviewtask.main.MainActivityContract

class MainActivityPresenter(
        private val view: MainActivityContract.View,
        private val router: MainActivityContract.Router
) : MainActivityContract.Presenter {

    override fun initialize() {
        view.showSearchFragment()
        // todo request permissions
    }

    override fun clear() {
        //todo
    }
}