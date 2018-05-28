package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.ChampionMastery
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

/**
 * Created by Rafa on 5/26/2017.
 */
interface ChampionMasteryService {

    @GET("champion-mastery/v3/champion-masteries/by-summoner/{summonerId}")
    fun getChampionMasteries(@Path("summonerId") summonerId: Long): Single<List<ChampionMastery>>

}