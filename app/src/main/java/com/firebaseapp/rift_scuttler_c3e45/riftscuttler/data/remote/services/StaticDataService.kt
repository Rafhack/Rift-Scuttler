package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Item
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Champion
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.MapDetail
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.StaticData
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.SummonerSpell
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Mastery
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single

/**
 * Created by Rafa on 5/26/2017.
 */
interface StaticDataService {

    @GET("static-data/v3/champions/{championId}")
    fun getChampionById(@Path("championId") championId: Long): Single<Champion>

    @GET("static-data/v3/champions")
    fun getAllChampions(@Query("dataById") byId: Boolean = true): Single<StaticData<Champion>>

    @GET("static-data/v3/maps")
    fun getMaps(): Single<StaticData<MapDetail?>>

    @GET("static-data/v3/summoner-spells")
    fun getAllSpells(@Query("dataById") byId: Boolean = true, @Query("tags") tags: String = "image"): Single<StaticData<SummonerSpell>>

    @GET("static-data/v3/masteries")
    fun getAllMasteries(@Query("dataById") byId: Boolean = true, @Query("tags") tags: String = "image"): Single<StaticData<Mastery>>

    @GET("static-data/v3/items")
    fun getAllItems(@Query("dataById") byId: Boolean = true, @Query("tags") tags: String = "image"): Single<StaticData<Item>>

    @GET("static-data/v3/versions")
    fun getVersions(): Single<List<String>>

}