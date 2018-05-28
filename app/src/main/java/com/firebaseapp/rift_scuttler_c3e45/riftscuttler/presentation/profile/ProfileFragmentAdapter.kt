package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ProfileFragmentAdapter(var fragments: List<Fragment>, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}