package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.summonersearch

import android.content.Context
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BasePresenter
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Region
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.RegionInteractor
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.SpectatorIInteractor
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.StaticDataInteractor
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.SummonerInteractor
import rx.android.schedulers.AndroidSchedulers

class SummonerSearchPresenter(view: SummonerSearchView) : BasePresenter<SummonerSearchView>(view) {

    private val summonerInteractor = SummonerInteractor()
    private val spectatorIInteractor = SpectatorIInteractor()
    private val regionInteractor = RegionInteractor()
    private val staticDataInteractor = StaticDataInteractor()

    fun getSummonerByName(summonerName: String) {
        view.setProgress(true)
        subscription = summonerInteractor.getSummonerByName(summonerName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onLoadSummoner(it)
                }, {
                    view.setProgress(false)
                    view.onError((view as Context).getString(R.string.rift_cannot_find_summoner))
                })
    }

    fun getActiveGame(summoner: Summoner) {
        view.setProgress(true)
        subscription = spectatorIInteractor.getActiveGame(summoner.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProgress(false)
                    view.onLoadCurrentGame(it, summoner)
                }, {
                    view.setProgress(false)
                    view.onNotCurrentPlaying(summoner)
                })
    }

    fun getSavedSummoner() {
        subscription = summonerInteractor.getLocalSavedSummonerName(view as Context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it.isEmpty())
                        getSummonerByName(it)
                    view.onLoadSavedSummonerName(it)
                })
    }

    fun saveSummonerName(summoner: Summoner) {
        summonerInteractor.saveSummonerNameToLocal(summoner, view as Context)
        summonerInteractor.saveSummonerNameToHistory(summoner.name, view as Context)
        RiftScuttlerApplication.summoner = summoner
    }

    fun getSavedRegion() {
        subscription = regionInteractor.getSavedRegion(view as Context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onLoadSavedRegion(it)
                    RiftScuttlerApplication.region = Region.from(it)
                })
    }

    fun saveRegion(region: String) {
        regionInteractor.saveRegion(region, view as Context)
        RiftScuttlerApplication.region = Region.from(region)
    }

    fun getSummonerNameHistory() {
        subscription = summonerInteractor.getSummonerNameHistory(view as Context)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onLoadSummonerNameHistory(it)
                })
    }

    fun getVersions() {
        subscription = staticDataInteractor.getVersions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProgress(false)
                    RiftScuttlerApplication.version = it.first()
                    view.onLoadVersions(it)
                }, {
                    view.setProgress(false)
                    view.onError((view as Context).getString(R.string.rift_cannot_get_version))
                })
    }

}