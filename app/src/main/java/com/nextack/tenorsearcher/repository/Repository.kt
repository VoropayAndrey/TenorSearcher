package com.nextack.tenorsearcher.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.nextack.tenorsearcher.constants.RestConstants
import com.nextack.tenorsearcher.data.SavedData
import com.nextack.tenorsearcher.rest.RestResponse
import com.nextack.tenorsearcher.rest.TenorRestService
import com.nextack.tenorsearcher.rest.responses.AnonInfo
import com.nextack.tenorsearcher.rest.responses.SearchResult
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import javax.security.auth.callback.Callback

class Repository(private val restService: TenorRestService,
                 private val savedData: SavedData) {


    fun search(query: String) : LiveData<RestResponse<SearchResult>> {
        val liveData: MutableLiveData<RestResponse<SearchResult>> = MutableLiveData()

        var anonId = savedData.getAnonId()
        if(anonId == null) {

        } else {
            restService.search(RestConstants.TENOR_API_KEY, anonId, query, "ua_UA", RestConstants.GIF_LIMIT).enqueue(object : retrofit2.Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    liveData.value = RestResponse(t)
                }

                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    liveData.value = RestResponse(response)
                }
            })
        }
        return liveData
    }

    fun trending() : LiveData<RestResponse<SearchResult>> {
        val liveData: MutableLiveData<RestResponse<SearchResult>> = MutableLiveData()

        var anonId = savedData.getAnonId()
        if(anonId == null) {

        } else {
            restService.trending(RestConstants.TENOR_API_KEY, anonId, "ua_UA", RestConstants.GIF_LIMIT, "basic", "all", "off", "").enqueue(object : retrofit2.Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    liveData.value = RestResponse(t)
                }

                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    liveData.value = RestResponse(response)
                }
            })
        }
        return liveData
    }

    fun getAnonId() : LiveData<RestResponse<AnonInfo>> {
        val liveData: MutableLiveData<RestResponse<AnonInfo>> = MutableLiveData()
        var anonId = savedData.getAnonId()
        if(anonId == null) {
            restService.getAnon(RestConstants.TENOR_API_KEY).enqueue(object : retrofit2.Callback<AnonInfo> {
                override fun onFailure(call: Call<AnonInfo>, t: Throwable) {
                    liveData.value = RestResponse(t)
                }

                override fun onResponse(call: Call<AnonInfo>, response: Response<AnonInfo>) {
                    liveData.value = RestResponse(response)
                    savedData.setAnonId(response.body()?.anonId)
                }
            })
        } else {
            liveData.value = RestResponse(AnonInfo(anonId))

        }
        return liveData
    }
}