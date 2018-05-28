package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.LeaguePosition
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.ServiceGenerator
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services.LeagueService
import rx.Single

/**
 * Created by Rafhack on 5/26/2017.
 */
class LeagueInteractor {

    private val cachedService: LeagueService
        get() = ServiceGenerator.createService(LeagueService::class.java, useForcedCache = true)

    private val service: LeagueService
        get() = ServiceGenerator.createService(LeagueService::class.java)

    fun getLeagueCachedPositions(summoerId: Long): Single<List<LeaguePosition>> = cachedService.getLeaguePosision(summoerId)

    fun getLeaguePositions(summoerId: Long): Single<List<LeaguePosition>> = service.getLeaguePosision(summoerId)

}