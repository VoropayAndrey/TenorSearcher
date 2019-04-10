package com.nextack.tenorsearcher.data

import android.content.SharedPreferences
import com.nextack.tenorsearcher.constants.DataConstants

class SavedData(private val sharedPreferences: SharedPreferences) {

    fun setAnonId(anonId: String?) {
        sharedPreferences.edit().putString(DataConstants.ANON_ID, anonId).commit()
    }

    fun getAnonId() : String? {
        return sharedPreferences.getString(DataConstants.ANON_ID, null)
    }
}