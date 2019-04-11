package com.nextack.tenorsearcher.viewmodel

import androidx.lifecycle.*
import com.nextack.tenorsearcher.repository.Repository
import com.nextack.tenorsearcher.rest.responses.RestResponse
import com.nextack.tenorsearcher.rest.responses.SearchResult
import javax.inject.Inject

class MainViewModel : ViewModel {

    val searchString: MutableLiveData<String> = MutableLiveData()
    private val repository: Repository
    var firstVisibleItem: Int = 0
    var restResult: MediatorLiveData<RestResponse<SearchResult>> = MediatorLiveData()

    @Inject
    constructor(repository: Repository) {
        this.repository = repository
    }

    fun getTrending() {
        restResult.addSource(repository.trending(), Observer {
            restResult.value = it
        })
    }

    fun search(query: String?) {
        restResult.addSource(repository.search(query), Observer {
            restResult.value = it
        })
    }

    fun next() {
        restResult.addSource(repository.next(), Observer {
            it.isNext = true
            restResult.value = it
        })
    }

    override fun onCleared() {
        super.onCleared()
    }
}