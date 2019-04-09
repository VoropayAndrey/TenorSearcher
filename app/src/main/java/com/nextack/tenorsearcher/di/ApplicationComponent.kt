package com.nextack.tenorsearcher.di

import com.nextack.tenorsearcher.repository.Repository
import com.nextack.tenorsearcher.view.MainActivity
import dagger.Component

@AppScope
@Component(modules = [ContextModule::class, DataModule::class, RestModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}