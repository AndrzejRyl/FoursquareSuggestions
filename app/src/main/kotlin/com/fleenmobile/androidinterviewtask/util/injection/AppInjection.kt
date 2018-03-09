package com.fleenmobile.androidinterviewtask.util.injection

import android.app.Application
import android.content.Context
import com.fleenmobile.androidinterviewtask.BaseApp
import com.fleenmobile.androidinterviewtask.util.injection.binding.ActivityBinderModule
import com.fleenmobile.androidinterviewtask.util.injection.binding.FragmentBinderModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RuntimeScope

@Module
class AppModule {

    @Provides
    fun context(application: Application): Context =
            application

    @Provides
    fun eventBus(): EventBus =
            EventBus.getDefault()

    @Provides
    fun compositeDisposable(): CompositeDisposable =
            CompositeDisposable()
}

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBinderModule::class,
    FragmentBinderModule::class,
    AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApp)
}
