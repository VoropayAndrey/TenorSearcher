package com.nextack.tenorsearcher.rest.responses

import java.util.*

class SearchResult (val weburl: String?, val results: List<TenorGif>) {
    fun toUrlList() : List<String> {
        val list: MutableList<String> = LinkedList()
        results.forEach {
            list.add(it.getGifUrl() ?: "")
        }
        return list
    }
}