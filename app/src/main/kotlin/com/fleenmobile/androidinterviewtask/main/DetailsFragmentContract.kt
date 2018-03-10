package com.fleenmobile.androidinterviewtask.main

import com.fleenmobile.androidinterviewtask.BaseContract

interface DetailsFragmentContract {

    interface View

    interface Router {
        fun navigateToSearch()
    }

    interface Presenter : BaseContract.Presenter
}