package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Item
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Champion
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.MapDetail
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.StaticData
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.SummonerSpell
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.ServiceGenerator
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services.StaticDataService
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Mastery
import rx.Single

/**
 * Created by Rafa on 5/26/2017.
 */
class StaticDataInteractor {

    private val serviceCached: StaticDataService
        get() = ServiceGenerator.createService(StaticDataService::class.java, useForcedCache = true)

    fun getChampionById(championId: Long): Single<Champion> = serviceCached.getChampionById(championId)

    fun getAllChampions(): Single<StaticData<Champion>> = serviceCached.getAllChampions()

    fun getMaps(): Single<StaticData<MapDetail?>> = serviceCached.getMaps()

    fun getAllSpells(): Single<StaticData<SummonerSpell>> = serviceCached.getAllSpells()

    fun getAllMasteries(): Single<StaticData<Mastery>> = serviceCached.getAllMasteries()

    fun getAllItems(): Single<StaticData<Item>> = serviceCached.getAllItems()

    fun getVersions(): Single<List<String>> = serviceCached.getVersions()
}