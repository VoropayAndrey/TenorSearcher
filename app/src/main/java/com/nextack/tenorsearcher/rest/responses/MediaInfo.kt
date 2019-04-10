package com.nextack.tenorsearcher.rest.responses

import com.google.gson.annotations.SerializedName

open class MediaInfo {
    // A url to a preview image of the media source
    @SerializedName("preview")
    var preview: String? = null

    // A url to the media source
    @SerializedName("url")
    var url: String? = null

    // Width and height in pixels
    @SerializedName("dims")
    var dims: IntArray? = null

    // Size of file in bytes
    @SerializedName("size")
    var size: Int = 0
}