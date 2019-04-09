package com.nextack.tenorsearcher.application

import android.app.Application
import com.nextack.tenorsearcher.di.ApplicationComponent
import com.nextack.tenorsearcher.di.ContextModule
import com.nextack.tenorsearcher.di.DaggerApplicationComponent

class TenorSearcherApplication : Application() {

    companion object {
        private lateinit var component: ApplicationComponent
        fun getApplicationComponent() : ApplicationComponent = component
    }

    override fun onCreate() {
        super.onCreate()
        component = initApplicationComponent()

    }

    private fun initApplicationComponent() : ApplicationComponent {
        return DaggerApplicationComponent.builder().contextModule(ContextModule(this.applicationContext)).build()
    }
}