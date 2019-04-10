package com.nextack.tenorsearcher.rest.responses

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.nextack.tenorsearcher.constants.RestConstants
import java.util.*
import kotlin.collections.HashMap

open class TenorGif {
    // a unix timestamp representing when this post was created.
    @SerializedName("created")
    var created: Float = 0F

    // true if this post contains audio (only video formats support audio, the gif image file format can not contain audio information).
    @SerializedName("hasaudio")
    var hasaudio: Boolean = false

    // Tenor result identifier
    @SerializedName("id")
    var id: String = ""

    // [ { GIF_FORMAT : MEDIA_OBJECT } ]
    // An array of dictionaries with GIF_FORMAT as the key and MEDIA_OBJECT as the value
    @SerializedName("media")
    var media: List<Map<String, Map<String, String>>> = ArrayList<HashMap<String, HashMap<String, String>>>()

    // An array of tags for the post
    @SerializedName("tags")
    var tags: List<String>? = null

    // The title of the post.
    @SerializedName("title")
    var title: String = ""

    // The full URL to view the post on tenor.com.
    @SerializedName("itemurl")
    var itemurl: String = ""

    // True if this post contains captions
    @SerializedName("hascaption")
    var hascaption: Boolean	= false

    // A short URL to view the post on tenor.com.
    @SerializedName("url")
    var url: String = ""

    fun getGifPreviewUrl() : String? {
        return media[0][RestConstants.FORMAT_GIF]?.get(RestConstants.MEDIA_PREVIEW)
    }

    fun getGifUrl() : String? {
        return media[0][RestConstants.FORMAT_GIF]?.get(RestConstants.MEDIA_URL)
    }

    fun getSmallestGifUrl() : String? {
        if(media[0].containsKey(RestConstants.FORMAT_NANO_GIF)) {
            return media[0][RestConstants.FORMAT_NANO_GIF]?.get(RestConstants.MEDIA_URL)
        } else if(media[0].containsKey(RestConstants.FORMAT_TINY_GIF)) {
            return media[0][RestConstants.FORMAT_TINY_GIF]?.get(RestConstants.MEDIA_URL)
        } else if(media[0].containsKey(RestConstants.FORMAT_MEDIUM_GIF)) {
            return media[0][RestConstants.FORMAT_MEDIUM_GIF]?.get(RestConstants.MEDIA_URL)
        } else {
            return media[0][RestConstants.FORMAT_GIF]?.get(RestConstants.MEDIA_URL)
        }
    }

    fun hasGif() : Boolean {
        return (media[0].containsKey(RestConstants.FORMAT_GIF) ||
            media[0].containsKey(RestConstants.FORMAT_MEDIUM_GIF) ||
            media[0].containsKey(RestConstants.FORMAT_TINY_GIF) ||
                media[0].containsKey(RestConstants.FORMAT_NANO_GIF))
    }

    fun hasMp4() : Boolean {
        return (media[0].containsKey(RestConstants.FORMAT_MP4) ||
            media[0].containsKey(RestConstants.FORMAT_LOOPED_MP4) ||
            media[0].containsKey(RestConstants.FORMAT_TINY_MP4) ||
            media[0].containsKey(RestConstants.FORMAT_NANO_MP4))
    }

    fun hasWebm() : Boolean {
        return (media[0].containsKey(RestConstants.FORMAT_WEBM) ||
            media[0].containsKey(RestConstants.FORMAT_TINY_WEBM) ||
            media[0].containsKey(RestConstants.FORMAT_NANO_WEBM))
    }


}