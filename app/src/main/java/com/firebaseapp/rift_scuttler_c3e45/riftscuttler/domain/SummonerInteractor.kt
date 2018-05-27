package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain

import android.content.Context
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.local.SPHelper
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.ServiceGenerator
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote.services.SummonerService
import rx.Single

/**
 * Created by Rafa on 5/25/2017.
 */

class SummonerInteractor {

    private val service: SummonerService
        get() = ServiceGenerator.createService(SummonerService::class.java)

    private val summonerIdKey: String = "SummonerId"

    fun getSummonerByName(summonerName: String): Single<Summoner> = service.getSummonerByName(summonerName)

    fun getSummonerById(summonerId: Long): Single<Summoner> = service.getSummonerById(summonerId)

    fun getLocalSavedSummonerName(context: Context): Single<String> {
        val sp = SPHelper.getInstance().getPreferences(context)
        return Single.just(if (sp.contains(summonerIdKey)) sp.getString(summonerIdKey, "") else "")
    }

    fun saveSummonerNameToLocal(summoner: Summoner, context: Context) {
        SPHelper.getInstance().getEditor(context).putString(summonerIdKey, summoner.name).commit()
    }
}
