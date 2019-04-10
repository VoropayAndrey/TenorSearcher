package com.nextack.tenorsearcher.utils

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import javax.inject.Inject

class ToastUtils {

    private lateinit var context: Context

    @Inject
    constructor(context: Context) {
        this.context = context
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.view.findViewById<TextView>(android.R.id.message)?.gravity = Gravity.CENTER
        toast.show()
    }

    fun showToast(resource: Int) {
        var message: String = context.resources.getString(resource)
        showToast(message)
    }
}