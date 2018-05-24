package com.firebaseapp.rift_scuttler_c3e45.riftscuttler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotterknife.bindView

class MainActivity : AppCompatActivity() {

    private val spnRegions by bindView<Spinner>(R.id.activity_main_spn_regions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSpinner()
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.regions, R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spnRegions.adapter = adapter
    }
}
