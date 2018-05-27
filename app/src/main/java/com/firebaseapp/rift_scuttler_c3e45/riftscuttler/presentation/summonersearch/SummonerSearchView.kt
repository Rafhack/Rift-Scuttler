package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.summonersearch

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseView
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.CurrentGameInfo
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner

interface SummonerSearchView : BaseView {

    fun onLoadSummoner(summoner: Summoner)
    fun onNotCurrentPlaying(summoner: Summoner)
    fun onLoadCurrentGame(currentGameInfo: CurrentGameInfo?, summoner: Summoner)
    fun onLoadSavedSummonerName(name: String)
    fun onLoadSavedRegion(region: String)

}