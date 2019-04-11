package com.nextack.tenorsearcher.rest

import com.nextack.tenorsearcher.rest.responses.AnonInfo
import com.nextack.tenorsearcher.rest.responses.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TenorRestService {

    @GET("anonid")
    fun getAnon(@Query("key") apiKey: String) : Call<AnonInfo>

    @GET("search")
    fun search(@Query("key") apiKey: String,
               @Query("anon_id") anonId: String,
               @Query("q") quary: String,
               @Query("locale") locale: String,
               @Query("limit") limit: Int,
               @Query("media_filter") mediaFilter: String,
               @Query("ar_range") range: String,
               @Query("contentfilter") contentFilter: String) : Call<SearchResult>

    @GET("trending")
    fun trending(@Query("key") apiKey: String,
                 @Query("anon_id") anonId: String,
                 @Query("locale") locale: String,
                 @Query("limit") limit: Int,
                 @Query("media_filter") mediaFilter: String,
                 @Query("ar_range") range: String,
                 @Query("contentfilter") contentFilter: String) : Call<SearchResult>

    @GET("trending")
    fun next(@Query("key") apiKey: String,
                 @Query("anon_id") anonId: String,
                 @Query("locale") locale: String,
                 @Query("limit") limit: Int,
                 @Query("media_filter") mediaFilter: String,
                 @Query("ar_range") range: String,
                 @Query("contentfilter") contentFilter: String,
                 @Query("pos") positionOffset: String) : Call<SearchResult>


}