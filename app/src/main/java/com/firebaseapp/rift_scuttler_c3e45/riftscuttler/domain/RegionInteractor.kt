package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import android.content.Context
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.local.SPHelper
import rx.Single

class RegionInteractor {

    private val regionIdKey: String = "region"

    fun getSavedRegion(context: Context): Single<String> {
        return Single.just(SPHelper.getInstance().getPreferences(context)
                .getString(regionIdKey, "na1"))
    }

    fun saveRegion(region: String, context: Context) {
        SPHelper.getInstance().getEditor(context).putString(regionIdKey, region).commit()
    }

}
