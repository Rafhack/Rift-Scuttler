package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.BuildConfig
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Champion

/**
 * Created by Rafa on 6/7/2017.
 */

fun ImageView.loadWithGlide(url: String?) {
    Glide.with(this.context)
            .load(url?.versionedImageUrl())
            .crossFade()
            .into(this)
}

fun Long.secondToMinute(): String {
    val minutes: Long = this / 60
    val seconds: Long = this % 60
    val timeStr: String = if (minutes < 10) "0$minutes" else minutes.toString()
    return timeStr.plus(if (seconds < 10) ":0$seconds" else ":$seconds")
}

fun Champion.squareUrl(): String = "champion/${this.key}.png"

fun String.versionedImageUrl(): String {
    return BuildConfig.STATIC_VERSIONED_URL.plus(this).format(RiftScuttlerApplication.version)
}