package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.LeaguePosition
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

/**
 * Created by Rafhack on 5/26/2017.
 */
interface LeagueService {

    @GET("league/v3/positions/by-summoner/{summonerId}")
    fun getLeaguePosision(@Path("summonerId") summonerId: Long): Single<List<LeaguePosition>>

}