package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

/**
 * Created by Rafa on 5/31/2017.
 */
open class GameParticipant : Summoner() {

    var championId: Long = 0
    var bot: Boolean = false
    var teamId: Long = 0
    var spell1Id: Long = 0
    var spell2Id: Long = 0

}