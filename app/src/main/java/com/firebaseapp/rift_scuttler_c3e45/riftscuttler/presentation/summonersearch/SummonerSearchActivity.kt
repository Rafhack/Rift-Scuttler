package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.summonersearch

import android.os.Bundle
import android.view.View
import android.widget.*
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseProgressActivity
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.CurrentGameInfo
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Region
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.local.SPHelper
import kotterknife.bindView

class SummonerSearchActivity : SummonerSearchView, BaseProgressActivity() {

    private val spnRegions by bindView<Spinner>(R.id.activity_summoner_search_spn_regions)
    private val btnSearch by bindView<Button>(R.id.activity_summoner_search_btn_search)
    private val edtSummonerName by bindView<EditText>(R.id.activity_summoner_search_edt_summoner_name)

    private val presenter = SummonerSearchPresenter(this)

    private val regionIdKey: String = "region"
    private val summonerIdKey: String = "SummonerId"

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_search)
        setupView()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    //endregion

    //region Private functions
    private fun setupView() {
        btnSearch.setOnClickListener { presenter.getSummonerByName(edtSummonerName.text.toString()) }
        setupSpinner()
    }

    private fun setupSpinner() {
        val adapter = RegionAdapter(Region.values())
        spnRegions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                SPHelper.getInstance().getEditor(this@SummonerSearchActivity).putString(regionIdKey,
                        adapter.regions?.get(position)?.endPoint).commit()
                RiftScuttlerApplication.region = adapter.regions?.get(position)
            }
        }
        spnRegions.adapter = adapter
        val defRegion: String = SPHelper.getInstance().getPreferences(this).getString(regionIdKey, "br1")
        spnRegions.setSelection(Region.values().indexOf(Region.from(defRegion)))
        RiftScuttlerApplication.region = Region.from(defRegion)
    }

    private fun init() {
        presenter.getSavedSummoner()
    }
    //endregion

    //region Overridden methods
    //region SummonerSearchView
    override fun onLoadSummoner(summoner: Summoner) {
        SPHelper.getInstance().getEditor(this).putString(summonerIdKey, summoner.name).commit()
        RiftScuttlerApplication.summoner = summoner
        presenter.getActiveGame(summoner)
    }

    override fun onNotCurrentPlaying(summoner: Summoner) {

    }

    override fun onLoadCurrentGame(currentGameInfo: CurrentGameInfo?, summoner: Summoner) {

    }

    override fun onLoadSavedSummonerName(name: String) {
        edtSummonerName.setText(name)
    }

    override fun onError(message: String) {

    }

    override fun setProgress(progress: Boolean) {
        if (progress) showProgress() else hideProgress()
    }
    //endregion
    //endregion

    //region Inner classes
    inner class RegionAdapter(regions: Array<Region>) : ArrayAdapter<String>(
            this, R.layout.simple_spinner_item) {

        var regions: Array<Region>? = null

        init {
            this.regions = regions
            setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        }

        override fun getCount(): Int = regions?.size as Int

        override fun getItem(position: Int): String = getString(regions?.get(position)?.strRes as Int)
    }
    //endregion

}
