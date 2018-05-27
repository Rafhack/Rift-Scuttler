package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

import java.io.Serializable
import java.util.*

/**
 * Created by Rafa on 5/31/2017.
 */

class CurrentGameInfo : Serializable {
    var gameId: Long = 0
    var gameStartTime: Calendar? = null
    var gameMode: String = ""
    var mapId: Long = 0
    var gameType: String = ""
    var participants: List<GameParticipant>? = null
    var gameLength: Long = 0
    var gameQueueConfigId: Long = 0
    var bannedChampions: List<BannedChampion>? = null
}