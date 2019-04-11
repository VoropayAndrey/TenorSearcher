package com.nextack.tenorsearcher.rest.responses

import com.google.gson.annotations.Expose
import retrofit2.Response

class RestResponse<T> {
    var statusCode: Int = 0
    var throwable: Throwable? = null
    var errorMessage: String? = null
    var body: T? = null

    @Expose(serialize = false, deserialize = false)
    var isNext: Boolean = false

    constructor(throwable: Throwable) {
        this.throwable = throwable
        statusCode = 500
        errorMessage = throwable.message
        body = null
    }

    constructor(response: Response<T>) {
        statusCode = response.code()
        statusCode = response.code()
        if(response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            errorMessage = response.errorBody()?.string()
            body = null
        }
    }

    constructor(body: T) {
        this.body = body
        statusCode = 200
    }

    constructor(error: String) {
        this.body = null
        this.errorMessage = error
        statusCode = 500
    }

    fun isSuccessful() : Boolean {
        if(statusCode in 200..299) {
            return true
        }
        return false
    }
}