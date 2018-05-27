package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.summonersearch

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseProgressActivity
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.CurrentGameInfo
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Region
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.Summoner
import kotterknife.bindView


class SummonerSearchActivity : SummonerSearchView, BaseProgressActivity() {

    private val spnRegions by bindView<Spinner>(R.id.activity_summoner_search_spn_regions)
    private val btnSearch by bindView<Button>(R.id.activity_summoner_search_btn_search)
    private val edtSummonerName by bindView<AutoCompleteTextView>(R.id.activity_summoner_search_edt_summoner_name)

    private val presenter = SummonerSearchPresenter(this)

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
        btnSearch.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(edtSummonerName.windowToken, 0)
            presenter.getSummonerByName(edtSummonerName.text.toString())
        }

        edtSummonerName.setOnEditorActionListener({ _: TextView, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_DONE)
                btnSearch.callOnClick()
            return@setOnEditorActionListener false
        })

        setupSpinner()
    }

    private fun setupAutoComplete(names: Set<String>?) {
        val adapter = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,
                names?.toTypedArray())
        edtSummonerName.setAdapter(adapter)
    }

    private fun setupSpinner() {
        val adapter = RegionAdapter(Region.values())
        spnRegions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.saveRegion(adapter.regions?.get(position)?.endPoint as String)
            }
        }
        spnRegions.adapter = adapter
        presenter.getSavedRegion()
    }

    private fun init() {
        presenter.getSavedSummoner()
        presenter.getSummonerNameHistory()
    }
    //endregion

    //region Overridden methods
    //region SummonerSearchView
    override fun onLoadSummoner(summoner: Summoner) {
        presenter.saveSummonerName(summoner)
        presenter.getActiveGame(summoner)
    }

    override fun onNotCurrentPlaying(summoner: Summoner) {

    }

    override fun onLoadCurrentGame(currentGameInfo: CurrentGameInfo?, summoner: Summoner) {

    }

    override fun onLoadSavedSummonerName(name: String) {
        edtSummonerName.setText(name)
    }

    override fun onLoadSummonerNameHistory(savedNames: Set<String>?) {
        setupAutoComplete(savedNames)
    }

    override fun onLoadSavedRegion(region: String) {
        spnRegions.setSelection(Region.values().indexOf(Region.from(region)))
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
