package com.nextack.tenorsearcher.repository

import androidx.lifecycle.LiveData
import com.nextack.tenorsearcher.rest.responses.RestResponse
import com.nextack.tenorsearcher.rest.responses.SearchResult

interface Repository {
    fun search(query: String?) : LiveData<RestResponse<SearchResult>>
    fun trending() : LiveData<RestResponse<SearchResult>>
    fun next() : LiveData<RestResponse<SearchResult>>
}