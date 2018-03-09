package com.fleenmobile.androidinterviewtask.main.ui

import com.fleenmobile.androidinterviewtask.BaseActivity
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.main.MainActivityContract

class MainActivity : BaseActivity<MainActivityContract.Presenter>(), MainActivityContract.View {

    override val layoutId: Int = R.layout.activity_main
}
