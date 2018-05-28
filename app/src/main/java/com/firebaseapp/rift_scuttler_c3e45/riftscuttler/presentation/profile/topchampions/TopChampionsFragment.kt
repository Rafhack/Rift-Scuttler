package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile.topchampions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R

class TopChampionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_champions, container, false)
        return view
    }

}