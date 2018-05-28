package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.ChampionMastery
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.ServiceGenerator
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services.ChampionMasteryService
import rx.Single

/**
 * Created by Rafa on 5/26/2017.
 */
class ChampionMasteryInteractor {

    private val service: ChampionMasteryService
        get() = ServiceGenerator.createService(ChampionMasteryService::class.java)


    fun getChampionMasteries(summonerId: Long): Single<List<ChampionMastery>> = service.getChampionMasteries(summonerId)

}