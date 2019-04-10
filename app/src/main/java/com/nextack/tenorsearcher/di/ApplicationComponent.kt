package com.nextack.tenorsearcher.di

import com.nextack.tenorsearcher.view.fragments.TrendingFragment
import com.nextack.tenorsearcher.view.activities.MainActivity
import dagger.Component

@AppScope
@Component(modules = [ContextModule::class, DataModule::class, RestModule::class, AppModule::class, UtilsModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: TrendingFragment)
}