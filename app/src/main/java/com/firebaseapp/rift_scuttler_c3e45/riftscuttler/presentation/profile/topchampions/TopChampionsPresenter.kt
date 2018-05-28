package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile.topchampions

import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base.BasePresenter
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.StaticDataInteractor
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.domain.ChampionMasteryInteractor
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
                    view.onError(it?.message as String)
                })
    }

}