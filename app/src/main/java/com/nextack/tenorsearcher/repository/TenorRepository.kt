package com.nextack.tenorsearcher.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nextack.tenorsearcher.constants.RestConstants
import com.nextack.tenorsearcher.data.SavedData
import com.nextack.tenorsearcher.rest.responses.RestResponse
import com.nextack.tenorsearcher.rest.TenorRestService
import com.nextack.tenorsearcher.rest.responses.AnonInfo
import com.nextack.tenorsearcher.rest.responses.SearchResult
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class TenorRepository(private val restService: TenorRestService,
                      private val savedData: SavedData) : Repository {

    interface AnonResponseListener {
        fun onResult(restResponse: RestResponse<AnonInfo>)
    }

    private var next: String? = null
    //private var oldQuery : String? = null

    override fun search(query: String?) : LiveData<RestResponse<SearchResult>> {
        val liveData: MutableLiveData<RestResponse<SearchResult>> = MutableLiveData()

        getAnonId(object : AnonResponseListener {
            override fun onResult(restResponse: RestResponse<AnonInfo>) {
                if(restResponse.isSuccessful()) {
                    var currentQuery: String = query!!

                    /*
                    if(query != null) {
                        currentQuery = query
                    } else {
                        currentQuery = oldQuery!!
                    }
                    */

                    var call: Call<SearchResult>

                    call = restService.search(
                        RestConstants.TENOR_API_KEY,
                        restResponse.body!!.anonId,
                        currentQuery,
                        RestConstants.DEFAULT_LOCALE,
                        RestConstants.GIF_LIMIT,
                        RestConstants.MEDIA_FILTER_MINIMAL,
                        RestConstants.AR_RANGE_ALL,
                        RestConstants.CONTENT_FILTER_OFF)


                    call.enqueue(object : retrofit2.Callback<SearchResult> {
                        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                            liveData.value = RestResponse(t)
                        }

                        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                            if(response.isSuccessful && response.body() != null) {
                                // Save next pointer
                                next = response.body()?.next
                                Timber.d("search, after request, next: $next")
                                liveData.value = RestResponse(response)
                            } else {
                                liveData.value = RestResponse(response.errorBody().toString())
                            }
                        }
                    })
                } else {
                   liveData.value = RestResponse(restResponse.throwable!!)
                }
            }
        })

        //oldQuery = query
        return liveData
    }

    override fun trending() : LiveData<RestResponse<SearchResult>> {
        val liveData: MutableLiveData<RestResponse<SearchResult>> = MutableLiveData()

        getAnonId(object : AnonResponseListener {
            override fun onResult(restResponse: RestResponse<AnonInfo>) {
                if(restResponse.isSuccessful()) {
                    var call: Call<SearchResult>

                    call = restService.trending(
                        RestConstants.TENOR_API_KEY,
                        restResponse.body!!.anonId,
                        RestConstants.DEFAULT_LOCALE,
                        RestConstants.GIF_LIMIT,
                        RestConstants.MEDIA_FILTER_MINIMAL,
                        RestConstants.AR_RANGE_ALL,
                        RestConstants.CONTENT_FILTER_OFF)

                    call.enqueue(object : retrofit2.Callback<SearchResult> {
                        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                            liveData.value = RestResponse(t)
                        }

                        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                            if(response.isSuccessful && response.body()?.results != null) {
                                // Save next pointer
                                next = response.body()?.next
                                Timber.d("trending, after request, next: $next")
                                liveData.value = RestResponse(response)
                            } else {
                                liveData.value = RestResponse(response.errorBody().toString())
                            }
                        }
                    })
                } else {
                    liveData.value = RestResponse(restResponse.throwable!!)
                }
            }
        })

        return liveData
    }

    override fun next(): LiveData<RestResponse<SearchResult>> {
        val liveData: MutableLiveData<RestResponse<SearchResult>> = MutableLiveData()

        getAnonId(object : AnonResponseListener {
            override fun onResult(restResponse: RestResponse<AnonInfo>) {
                if (restResponse.isSuccessful()) {
                    Timber.d("before request, next: $next")
                    restService.next(
                        RestConstants.TENOR_API_KEY,
                        restResponse.body!!.anonId,
                        RestConstants.DEFAULT_LOCALE,
                        RestConstants.GIF_LIMIT,
                        RestConstants.MEDIA_FILTER_MINIMAL,
                        RestConstants.AR_RANGE_ALL,
                        RestConstants.CONTENT_FILTER_OFF,
                        next!!).enqueue(object : retrofit2.Callback<SearchResult> {
                        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                            liveData.value = RestResponse(t)
                        }

                        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                            if(response.isSuccessful && response.body()?.results != null) {
                                // Save next pointer
                                next = response.body()?.next
                                Timber.d("after request, next: $next")
                                liveData.value = RestResponse(response)
                            } else {
                                liveData.value = RestResponse(response.errorBody().toString())
                            }
                        }
                    })
                } else {
                    liveData.value = RestResponse(restResponse.throwable!!)
                }
            }
        })
        return liveData
    }

    private fun getAnonId(anonInfoListener: AnonResponseListener) {
        var anonId = savedData.getAnonId()
        if(anonId == null) {
            restService.getAnon(RestConstants.TENOR_API_KEY).enqueue(object : retrofit2.Callback<AnonInfo> {
                override fun onFailure(call: Call<AnonInfo>, t: Throwable) {
                    anonInfoListener.onResult(RestResponse(t))
                }

                override fun onResponse(call: Call<AnonInfo>, response: Response<AnonInfo>) {
                    anonInfoListener.onResult(RestResponse(response))
                    savedData.setAnonId(response.body()?.anonId)
                }
            })
        } else {
            anonInfoListener.onResult(RestResponse(AnonInfo(anonId)))
        }
    }
}