package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile.topchampions

import android.content.Context
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BasePresenter
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.ChampionMasteryInteractor
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.StaticDataInteractor
import rx.Single
import rx.android.schedulers.AndroidSchedulers

class TopChampionsPresenter(view: TopChampionsView) : BasePresenter<TopChampionsView>(view) {

    private val champMasteryInteractor: ChampionMasteryInteractor = ChampionMasteryInteractor()
    private val staticDataInteractor: StaticDataInteractor = StaticDataInteractor()

    fun getChampsAndMasteries(summonerId: Long) {
        view.setProgress(true)
        subscription = Single.zip(
                champMasteryInteractor.getChampionMasteries(summonerId),
                staticDataInteractor.getAllChampions(), { t1, t2 -> Pair(t1, t2) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProgress(false)
                    view.onLoadChampAndMasteries(it.second, it.first)
                }, {
                    view.setProgress(false)
                    view.onError((view as Context).getString(R.string.rift_cannot_get_champions))
                })
    }

}