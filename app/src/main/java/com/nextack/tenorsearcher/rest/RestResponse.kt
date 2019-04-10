package com.nextack.tenorsearcher.rest

import retrofit2.Response

class RestResponse<T> {
    var statusCode: Int = 0
    var errorMessage: String? = null
    var body: T? = null

    constructor(error: Throwable) {
        statusCode = 500
        errorMessage = error.message
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
    }

    fun isSuccessful() : Boolean {
        if(statusCode in 200..299) {
            return true
        }
        return false
    }
}