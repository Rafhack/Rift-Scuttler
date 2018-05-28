package com.firebaseapp.rift_scuttler_c3e45.riftscuttler

import android.app.Application
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Region
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner

/**
 * Created by Rafa on 5/30/2017.
 */
class RiftScuttlerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object Persistance {
        var region: Region? = null
        var application: RiftScuttlerApplication? = null
        var summoner: Summoner? = null
        var version: String = ""
    }
}