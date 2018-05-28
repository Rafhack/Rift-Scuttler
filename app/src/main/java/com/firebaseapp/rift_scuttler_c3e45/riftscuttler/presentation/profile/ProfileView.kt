package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseView
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.LeaguePosition

interface ProfileView : BaseView {
    fun onLoadLeaguePositions(leaguePosition: List<LeaguePosition>?)
}