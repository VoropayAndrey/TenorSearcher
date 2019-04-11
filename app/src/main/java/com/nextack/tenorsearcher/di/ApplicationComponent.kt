package com.nextack.tenorsearcher.di

import com.nextack.tenorsearcher.utils.ToastUtils
import com.nextack.tenorsearcher.view.fragments.TrendingFragment
import com.nextack.tenorsearcher.view.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(dependencies = [], modules = [ContextModule::class,
    DataModule::class,
    RestModule::class,
    AppModule::class,
    UtilsModule::class,
    MainViewModelModule::class,
    ViewModelFactoryModule::class,
    MainViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: TrendingFragment)

}