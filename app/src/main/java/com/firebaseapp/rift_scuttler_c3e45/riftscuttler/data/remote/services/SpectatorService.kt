package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.CurrentGameInfo
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

/**
 * Created by Rafa on 5/31/2017.
 */

interface SpectatorService {

    @GET("spectator/v3/active-games/by-summoner/{summonerId}")
    fun getActiveGame(@Path("summonerId") summonerId: Long): Single<CurrentGameInfo>

}