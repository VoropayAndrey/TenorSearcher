package com.nextack.tenorsearcher.rest.responses

import com.google.gson.annotations.SerializedName

open class AnonInfo {
    @SerializedName("anon_id")
    var anonId: String = ""

    constructor(id: String) {
        anonId = id
    }
}