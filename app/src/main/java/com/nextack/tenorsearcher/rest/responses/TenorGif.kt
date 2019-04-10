package com.nextack.tenorsearcher.rest.responses

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
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
    var media: List<Map<String, String>> = ArrayList<HashMap<String, String>>()

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


}