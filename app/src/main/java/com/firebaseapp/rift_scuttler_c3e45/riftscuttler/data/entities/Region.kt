package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R

/**
 * Created by Rafa on 5/30/2017.
 */
enum class Region constructor(strRes: Int, endpoint: String, name: String) {
    BR(R.string.rift_region_brazil, "br1", "BR"),
    EUNE(R.string.rift_region_eune, "eun1", "EUNE"),
    EUW(R.string.rift_region_euw, "euw1", "EUW"),
    JP(R.string.rift_region_jp, "jp1", "JP"),
    KR(R.string.rift_region_kr, "kr", "KR"),
    LAN(R.string.rift_region_lan, "la1", "LAN"),
    LAS(R.string.rift_region_las, "la2", "LAS"),
    NA(R.string.rift_region_na, "na1", "NA"),
    OCE(R.string.rift_region_oce, "oc1", "OCE"),
    TR(R.string.rift_region_tr, "tr1", "TR"),
    RU(R.string.rift_region_ru, "ru", "RU"),
    PBE(R.string.rift_region_pbe, "pbe1", "PBE");

    var endPoint: String internal set
    var regionName: String internal set
    var strRes: Int internal set

    init {
        this.strRes = strRes
        this.endPoint = endpoint
        this.regionName = name
    }

    companion object {
        fun from(findValue: String): Region = Region.values().first { it.regionName == findValue }
    }
}