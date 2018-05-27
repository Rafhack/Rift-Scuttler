package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R

/**
 * Created by Rafhack on 5/26/2017.
 */

class SPHelper private constructor() {

    @SuppressLint("CommitPrefEdits")
    fun getEditor(context: Context): SharedPreferences.Editor = context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE).edit()

    fun getPreferences(context: Context): SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE)

    companion object {

        private var aInstance: SPHelper? = null

        fun getInstance(): SPHelper {
            if (aInstance == null) aInstance = SPHelper()
            return aInstance as SPHelper
        }
    }

}
