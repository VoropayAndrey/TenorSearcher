package com.nextack.tenorsearcher.di

import android.content.Context
import android.content.SharedPreferences
import com.nextack.tenorsearcher.constants.DataConstants
import com.nextack.tenorsearcher.data.SavedData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @AppScope
    fun provideSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(DataConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @AppScope
    fun provideSavedData(sharedPreferences: SharedPreferences) : SavedData {
        return SavedData(sharedPreferences)
    }
}