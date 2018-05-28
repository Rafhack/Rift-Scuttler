package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile

import android.os.Bundle
import android.widget.TextView
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseProgressActivity
import kotterknife.bindView

class ProfileActivity : BaseProgressActivity(), ProfileView {

    private val tvwSummonerName by bindView<TextView>(R.id.activity_profile_tvw_summoner_name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        tvwSummonerName.text = RiftScuttlerApplication.summoner?.name
    }

    override fun onError(message: String) {

    }

    override fun setProgress(progress: Boolean) {

    }
}