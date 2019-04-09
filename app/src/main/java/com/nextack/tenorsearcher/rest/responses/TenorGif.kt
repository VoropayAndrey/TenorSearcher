package com.nextack.tenorsearcher.rest.responses

class TenorGif {
    // a unix timestamp representing when this post was created.
    var created: Float = 0F

    // true if this post contains audio (only video formats support audio, the gif image file format can not contain audio information).
    var hasaudio: Boolean = false

    // Tenor result identifier
    var id: String = ""

    // [ { GIF_FORMAT : MEDIA_OBJECT } ]
    // An array of dictionaries with GIF_FORMAT as the key and MEDIA_OBJECT as the value
    //var media

    // An array of tags for the post
    var tags: List<String>? = null

    // The title of the post.
    var title: String = ""

    // The full URL to view the post on tenor.com.
    var itemurl: String = ""

    // True if this post contains captions
    var hascaption: Boolean	= false

    // A short URL to view the post on tenor.com.
    var url: String = ""


}