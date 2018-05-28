package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

/**
 * Created by Rafhack on 5/26/2017.
 */
class LeaguePosition {

    companion object QUEUE_TYPE {
        @Transient const val QT_SOLO: String = "RANKED_SOLO_5x5"
        @Transient const val QT_FLEX: String = "RANKED_FLEX_SR"
        @Transient const val QT_TTLINE: String = "RANKED_FLEX_TT"
    }

    var rank: String = ""
    var queueType: String = ""
    var hotStreak: Boolean = false
    var wins: Int = 0
    var veteran: Boolean = false
    var losses: Int = 0
    var playerOrTeamId: String = ""
    var miniSeries: MiniSerie? = null
    var leagueName: String = ""
    var playerOrTeamName: String = ""
    var inactive: Boolean = false
    var freshBlood: Boolean = false
    var tier: String = ""
    var leaguePoints: Int = 0
}