package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.summonersearch

import android.content.Context
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BasePresenter
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.SpectatorIInteractor
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.SummonerInteractor
import rx.android.schedulers.AndroidSchedulers

class SummonerSearchPresenter(view: SummonerSearchView) : BasePresenter<SummonerSearchView>(view) {

    private val summonerInteractor = SummonerInteractor()
    private val spectatorIInteractor = SpectatorIInteractor()

    fun getSummonerByName(summonerName: String) {
        view.setProgress(true)
        subscription = summonerInteractor.getSummonerByName(summonerName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProgress(false)
                    view.onLoadSummoner(it)
                }, {
                    view.setProgress(false)
                    view.onError(it?.message as String)
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
                    view.onError(it?.message as String)
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

}