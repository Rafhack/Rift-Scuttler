package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile.topchampions

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseView
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.ChampionMastery
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Champion
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.StaticData

interface TopChampionsView : BaseView {
    fun onLoadChampAndMasteries(champs: StaticData<Champion>?, masteries: List<ChampionMastery>?)
}