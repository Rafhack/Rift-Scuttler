package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * Created by Rafa on 5/25/2017.
 */

open class Summoner : Serializable {

    var profileIconId: Int = 0
    @SerializedName(value = "name", alternate = ["summonerName"])
    var name: String = ""
    var summonerLevel: Long = 0
    var revisionDate: Calendar? = null
    @SerializedName(value = "id", alternate = ["summonerId"])
    var id: Long = 0
    var accountId: Long = 0
}
