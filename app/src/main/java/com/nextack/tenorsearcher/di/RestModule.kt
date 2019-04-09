package com.nextack.tenorsearcher.di

import com.nextack.tenorsearcher.constants.RestConstants
import com.nextack.tenorsearcher.rest.TenorRestService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        var httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        var okHttpClientBuiled: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuiled.addInterceptor(httpLoggingInterceptor)
        return okHttpClientBuiled.build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) : Retrofit {
        var retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        retrofitBuilder.baseUrl(RestConstants.BASE_URL)
        retrofitBuilder.client(okHttpClient)
        retrofitBuilder.addConverterFactory(gsonConverterFactory)
        return retrofitBuilder.build()
    }

    @Provides
    @AppScope
    fun provideTenorRestService(retrofit: Retrofit) : TenorRestService {
        return retrofit.create(TenorRestService::class.java)
    }
}