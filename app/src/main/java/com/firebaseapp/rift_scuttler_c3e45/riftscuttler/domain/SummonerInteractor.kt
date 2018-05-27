package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.ServiceGenerator
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services.SummonerService
import rx.Single

/**
 * Created by Rafa on 5/25/2017.
 */

class SummonerInteractor {

    private val service: SummonerService
        get() = ServiceGenerator.createService(SummonerService::class.java)

    fun getSummonerByName(summonerName: String): Single<Summoner> = service.getSummonerByName(summonerName)

    fun getSummonerById(summonerId: Long): Single<Summoner> = service.getSummonerById(summonerId)
}
