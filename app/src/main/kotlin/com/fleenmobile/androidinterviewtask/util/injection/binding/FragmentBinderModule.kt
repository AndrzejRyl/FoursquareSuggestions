package com.fleenmobile.androidinterviewtask.util.injection.binding

import android.support.v4.app.Fragment
import com.fleenmobile.androidinterviewtask.main.ui.DetailsFragment
import com.fleenmobile.androidinterviewtask.main.ui.SearchFragment
import com.fleenmobile.androidinterviewtask.util.injection.module.DetailsFragmentModule
import com.fleenmobile.androidinterviewtask.util.injection.module.SearchFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBinderModule {

    @Binds
    abstract fun fragment(fragment: Fragment): Fragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [DetailsFragmentModule::class])
    abstract fun bindDetailsFragment(): DetailsFragment
}
