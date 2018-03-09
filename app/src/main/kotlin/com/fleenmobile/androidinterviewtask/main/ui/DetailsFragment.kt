package com.fleenmobile.androidinterviewtask.main.ui

import com.fleenmobile.androidinterviewtask.BaseFragment
import com.fleenmobile.androidinterviewtask.R
import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract

class DetailsFragment : BaseFragment<DetailsFragmentContract.Presenter>(), DetailsFragmentContract.View {

    companion object {
        const val TAG = "DetailsFragment"
    }

    override val layoutId: Int = R.layout.fragment_details
}