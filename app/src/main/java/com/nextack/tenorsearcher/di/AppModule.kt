package com.nextack.tenorsearcher.di

import com.nextack.tenorsearcher.data.SavedData
import com.nextack.tenorsearcher.repository.Repository
import com.nextack.tenorsearcher.repository.TenorRepository
import com.nextack.tenorsearcher.rest.TenorRestService
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    @AppScope
    fun provideRepository(restService: TenorRestService, savedData: SavedData) : Repository {
        return TenorRepository(restService, savedData)
    }
}