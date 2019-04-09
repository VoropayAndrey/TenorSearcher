package com.nextack.tenorsearcher.rest

import com.nextack.tenorsearcher.rest.responses.AnonInfo
import com.nextack.tenorsearcher.rest.responses.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TenorRestService {

    @GET("anonid")
    fun getAnon(@Header("key") apiKey: String) : Call<AnonInfo>

    @GET("search")
    fun search(@Header("key") apiKey: String,
               @Header("anon_id") anonId: String,
               @Header("q") quary: String,
               @Header("locale") locale: String,
               @Header("limit") limit: Int) : Call<List<SearchResult>>

    @GET("trending")
    fun trending(@Header("key") apiKey: String,
                 @Header("anon_id") anonId: String,
                 @Header("locale") locale: String,
                 @Header("limit") limit: Int,
                 @Header("media_filter") mediaFilter: String,
                 @Header("ar_range") range: String,
                 @Header("contentfilter") contentFilter: String,
                 @Header("pos") positionOffset: String) : Call<List<SearchResult>>


}