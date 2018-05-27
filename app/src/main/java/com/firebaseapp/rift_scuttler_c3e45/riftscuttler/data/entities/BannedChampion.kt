package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

import java.io.Serializable

/**
 * Created by Rafa on 5/31/2017.
 */
class BannedChampion: Serializable {
    var pickTurn: Int = 0
    var championId: Long = 0
    var teamId: Long = 0
}