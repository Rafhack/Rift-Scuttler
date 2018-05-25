package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.summonersearch

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.firebaseapp.rift_scuttler_c3e45.base.BaseProgressActivity
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import kotterknife.bindView

class SummonerSearchActivity : BaseProgressActivity() {

    private val spnRegions by bindView<Spinner>(R.id.activity_summoner_search_spn_regions)
    private val btnSearch by bindView<Button>(R.id.activity_summoner_search_btn_search)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_search)
        setupSpinner()
        setupListener()
    }

    private fun setupListener() {
        btnSearch.setOnClickListener { showProgress() }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.regions, R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spnRegions.adapter = adapter
    }

}
