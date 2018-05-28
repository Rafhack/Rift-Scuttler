package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BasePresenter
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.LeagueInteractor
import rx.android.schedulers.AndroidSchedulers

class ProfilePresenter(view: ProfileView) : BasePresenter<ProfileView>(view) {

    private val leagueInteractor = LeagueInteractor()

    fun getLeaguePositions(summonerId: Long) {
        view.setProgress(true)
        subscription = leagueInteractor.getLeaguePositions(summonerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    view.setProgress(false)
                    view.onLoadLeaguePositions(it)
                }, {
                    view.setProgress(false)
                    view.onError(it?.message as String)

                })
    }

}