package com.nextack.tenorsearcher.di

import android.content.Context
import com.nextack.tenorsearcher.utils.ToastUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {
    @Provides
    @AppScope
    fun provideToastUtils(context: Context) : ToastUtils = ToastUtils(context)
}