package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile

import android.os.Bundle
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BaseProgressActivity

class ProfileActivity : BaseProgressActivity(), ProfileView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onError(message: String) {

    }

    override fun setProgress(progress: Boolean) {

    }
}