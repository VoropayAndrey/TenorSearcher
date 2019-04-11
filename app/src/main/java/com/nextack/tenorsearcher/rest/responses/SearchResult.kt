package com.nextack.tenorsearcher.rest.responses

import java.util.*

class SearchResult (val weburl: String?, val results: List<TenorGif>, val next: String) {
    fun toUrlList() : List<String> {
        val list: MutableList<String> = LinkedList()
        results.forEach {
            list.add(it.getGifUrl() ?: "")
        }
        return list
    }

    fun toPreviewUrlList() : List<String> {
        val list: MutableList<String> = LinkedList()
        results.forEach {
            list.add(it.getSmallestGifUrl() ?: "")
        }
        return list
    }
}