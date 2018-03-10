package com.fleenmobile.androidinterviewtask.util.injection.module

import android.content.Context
import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract
import com.fleenmobile.androidinterviewtask.main.adapter.DetailsPhotosAdapter
import com.fleenmobile.androidinterviewtask.main.presentation.DetailsFragmentPresenter
import com.fleenmobile.androidinterviewtask.main.ui.DetailsFragment
import dagger.Module
import dagger.Provides

@Module
class DetailsFragmentModule {

    @Provides
    fun view(detailsFragment: DetailsFragment): DetailsFragmentContract.View = detailsFragment

    @Provides
    fun presenter(view: DetailsFragmentContract.View)
            : DetailsFragmentContract.Presenter = DetailsFragmentPresenter(view)

    @Provides
    fun adapter(context: Context): DetailsPhotosAdapter =
            DetailsPhotosAdapter(context, arrayListOf())
}