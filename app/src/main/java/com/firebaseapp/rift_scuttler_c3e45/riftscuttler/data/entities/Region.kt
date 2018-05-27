package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R

/**
 * Created by Rafa on 5/30/2017.
 */
enum class Region constructor(strRes: Int, endpoint: String) {
    BR(R.string.rift_region_brazil, "br1"),
    EUNE(R.string.rift_region_eune, "eun1"),
    EUW(R.string.rift_region_euw, "euw1"),
    JP(R.string.rift_region_jp, "jp1"),
    KR(R.string.rift_region_kr, "kr"),
    LAN(R.string.rift_region_lan, "la1"),
    LAS(R.string.rift_region_las, "la2"),
    NA(R.string.rift_region_na, "na1"),
    OCE(R.string.rift_region_oce, "oc1"),
    TR(R.string.rift_region_tr, "tr1"),
    RU(R.string.rift_region_ru, "ru"),
    PBE(R.string.rift_region_pbe, "pbe1");

    var endPoint: String internal set
    var strRes: Int internal set

    init {
        this.strRes = strRes
        this.endPoint = endpoint
    }

    companion object {
        fun from(findValue: String): Region = Region.values().first { it.endPoint == findValue }
    }
}