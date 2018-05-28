package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseProgressActivity
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.LeaguePosition
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile.topchampions.TopChampionsFragment
import kotterknife.bindView
import me.relex.circleindicator.CircleIndicator

class ProfileActivity : BaseProgressActivity(), ProfileView {

    private val tvwSummonerName by bindView<TextView>(R.id.activity_profile_tvw_summoner_name)
    private val ciViewPager by bindView<CircleIndicator>(R.id.activity_profile_ci_indicator)
    private val vpgFragments by bindView<ViewPager>(R.id.activity_profile_vpg_fragments)
    private val imgTierStrip by bindView<ImageView>(R.id.activity_profile_img_strip)

    private val topChampionsFragment = TopChampionsFragment()
    private val summoner = RiftScuttlerApplication.summoner
    private val presenter = ProfilePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        setupView()
    }

    private fun init() {
        presenter.getLeaguePositions(summoner?.id as Long)

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(topChampionsFragment)

        val adapter = ProfileFragmentAdapter(fragments, supportFragmentManager)
        vpgFragments.adapter = adapter
        ciViewPager.setViewPager(vpgFragments)
    }

    private fun setupView() {
        tvwSummonerName.text = summoner?.name
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onError(message: String) {

    }

    override fun setProgress(progress: Boolean) {
        if (progress) showProgress() else hideProgress()
    }

    override fun onLoadLeaguePositions(leaguePosition: List<LeaguePosition>?) {
        leaguePosition?.forEach {
            if (it.queueType == LeaguePosition.QT_SOLO)
                Glide.with(imgTierStrip.context)
                        .load(getTierIcon(it.tier))
                        .crossFade()
                        .into(imgTierStrip)
        }
    }

    private fun getTierIcon(tier: String): Int = when (tier) {
        "BRONZE" -> R.drawable.bronze_strip
        "SILVER" -> R.drawable.silver_strip
        "GOLD" -> R.drawable.gold_strip
        "PLATINUM" -> R.drawable.platinum_strip
        "DIAMOND" -> R.drawable.diamond_strip
        "MASTER" -> R.drawable.master_strip
        "CHALLENGER" -> R.drawable.challenger_strip
        else -> R.drawable.bronze_strip
    }
}