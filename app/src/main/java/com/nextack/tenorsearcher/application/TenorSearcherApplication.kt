package com.nextack.tenorsearcher.application

import android.app.Application
import com.nextack.tenorsearcher.BuildConfig
import com.nextack.tenorsearcher.di.ApplicationComponent
import com.nextack.tenorsearcher.di.ContextModule
import com.nextack.tenorsearcher.di.DaggerApplicationComponent
import com.nextack.tenorsearcher.logs.CrashReportingTree
import timber.log.Timber

open class TenorSearcherApplication : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = initApplicationComponent()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun initApplicationComponent() : ApplicationComponent {
        return DaggerApplicationComponent.builder().contextModule(ContextModule(this.applicationContext)).build()
    }

    fun getApplicationComponent() : ApplicationComponent = component
}