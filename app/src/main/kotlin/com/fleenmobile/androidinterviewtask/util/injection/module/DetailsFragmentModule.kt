package com.fleenmobile.androidinterviewtask.util.injection.module

import android.content.Context
import com.fleenmobile.androidinterviewtask.main.DetailsFragmentContract
import com.fleenmobile.androidinterviewtask.main.adapter.DetailsPhotosAdapter
import com.fleenmobile.androidinterviewtask.main.navigation.DetailsFragmentRouter
import com.fleenmobile.androidinterviewtask.main.presentation.DetailsFragmentPresenter
import com.fleenmobile.androidinterviewtask.main.ui.DetailsFragment
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus

@Module
class DetailsFragmentModule {

    @Provides
    fun view(detailsFragment: DetailsFragment): DetailsFragmentContract.View = detailsFragment

    @Provides
    fun router(eventBus: EventBus): DetailsFragmentContract.Router = DetailsFragmentRouter(eventBus)

    @Provides
    fun presenter(view: DetailsFragmentContract.View, router: DetailsFragmentContract.Router)
            : DetailsFragmentContract.Presenter = DetailsFragmentPresenter(view, router)

    @Provides
    fun adapter(context: Context): DetailsPhotosAdapter =
            DetailsPhotosAdapter(context, arrayListOf())
}