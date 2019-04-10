package com.nextack.tenorsearcher.constants

object RestConstants {
    val BASE_URL = "https://api.tenor.com/v1/"
    val TENOR_API_KEY = "WG5AK2U22EFL"
    val GIF_LIMIT = 50


    /*
    Resolution and size: High quality GIF format, largest file size available
    Dimensions: Original upload dimensions (no limits)
    Usage Notes: Use this size for GIF shares on desktop

    Filter: default, basic, minimal
    */
    val FORMAT_GIF = "gif"

    /*
    Resolution and size: small reduction in size of the GIF format
    Dimensions: Original upload dimensions (no limits) but much higher compression rate
    Usage Notes: Use this size for GIF previews on desktop

    Filter: default
    */
    val FORMAT_MEDIUM_GIF = "mediumgif"

    /*
    Resolution and size: reduced size of the GIF format
    Dimensions: Up to 220 pixels wide, Height scaled with aspect ratio preserved
    Usage Notes: Use this size for GIF previews and shares on mobile

    Filter: default, basic, minimal
    */
    val FORMAT_TINY_GIF = "tinygif"

    /*
    Resolution and size: smallest size of the GIF format
    Dimensions: Up to 90 pixels tall, Width scaled with aspect ratio preserved
    Usage Notes: Use this size for GIF previews on mobile

    Filter: default, basic
    */
    val FORMAT_NANO_GIF = "nanogif"

    /*
    Resolution and size: highest quality video format, largest of the video formats, but smaller than GIF
    Dimensions: Similar to gif, but padded to fit video container specifications (usually 8-pixel increments)
    Usage Notes: Use this size for MP4 previews and shares on desktop

    Filter: default, basic, minimal
    */
    val FORMAT_MP4 = "mp4"

    /*
    Resolution and size: highest quality video format, larger in size than mp4
    Dimensions: Same as mp4
    Usage Notes: Use this size for mp4 shares if you want the video clip to run a few times rather than only once

    Filter: default
    */
    val FORMAT_LOOPED_MP4 = "loopedmp4"

    /*
    Resolution and size: reduced size of the mp4 format
    Dimensions: Variable width and height, with maximum bounding box of 320x320
    Usage Notes: Use this size for mp4 previews and shares on mobile

    Filter: default, basic
    */
    val FORMAT_TINY_MP4 = "tinymp4"

    /*
    Resolution and size: smallest size of the mp4 format
    Dimensions: Variable width and height, with maximum bounding box of 150x150
    Usage Notes: Use this size for mp4 previews on mobile

    Filter: default, basic
    */
    val FORMAT_NANO_MP4 = "nanomp4"

    /*
    Resolution and size: Lower quality video format, smaller in size than MP4
    Dimensions: Same as mp4
    Usage Notes: Use this size for webm previews and shares on desktop

    Filter: default
    */
    val FORMAT_WEBM = "webm"

    /*
    Resolution and size: reduced size of the webm format
    Dimensions: Same as tinymp4
    Usage Notes: Use this size for GIF shares on mobile

   Filter: default
   */
    val FORMAT_TINY_WEBM = "tinywebm"

    /*
    Resolution and size: smallest size of the webm format
    Dimensions: Same as nanomp4
    Usage Notes: Use this size for GIF previews on mobile

    Filter: default
    */
    val FORMAT_NANO_WEBM = "nanowebm"


    /*
    A url to a preview image of the media source
     */
    var MEDIA_PREVIEW = "preview"


    /*
    A a url to the media source
    */
    var MEDIA_URL = "url"

    /*
    Width and height in pixels
    */
    var MEDIA_DIMS = "dims"

    /*
    Size of file in bytes
    */
    var MEDIA_SIZE = "size"
}