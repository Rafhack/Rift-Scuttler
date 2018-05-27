package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.CurrentGameInfo
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.ServiceGenerator
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services.SpectatorService
import rx.Single

/**
 * Created by Rafa on 5/31/2017.
 */
class SpectatorIInteractor {

    private val service: SpectatorService
        get() = ServiceGenerator.createService(SpectatorService::class.java)

    fun getActiveGame(summonerId: Long): Single<CurrentGameInfo> = service.getActiveGame(summonerId)

}