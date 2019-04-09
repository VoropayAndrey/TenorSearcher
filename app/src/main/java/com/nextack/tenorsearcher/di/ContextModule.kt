package com.nextack.tenorsearcher.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {
    @Provides
    fun provideContext() : Context = context
}