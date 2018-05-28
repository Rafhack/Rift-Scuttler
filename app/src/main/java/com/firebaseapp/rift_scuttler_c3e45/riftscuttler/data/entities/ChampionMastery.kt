package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

import java.util.*

/**
 * Created by Rafa on 5/26/2017.
 */
class ChampionMastery {

    var chestGranted: Boolean = false
    var championLevel: Int = 0
    var championPoints: Int = 0
    var championId: Long = 0
    var playerId: Long = 0
    var championPointsUntilNextLevel: Long = 0
    var championPointsSinceLastLevel: Long = 0
    var lastPlayTime: Calendar? = null

}