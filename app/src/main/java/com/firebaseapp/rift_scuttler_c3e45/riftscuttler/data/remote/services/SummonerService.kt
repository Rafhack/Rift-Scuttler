package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

/**
 * Created by Rafa on 5/25/2017.
 */

interface SummonerService {

    @GET("summoner/v3/summoners/by-name/{summonerName}")
    fun getSummonerByName(@Path("summonerName") summonerName: String): Single<Summoner>

    @GET("summoner/v3/summoners/{summonerId}")
    fun getSummonerById(@Path("summonerId") summonerId: Long): Single<Summoner>

}
