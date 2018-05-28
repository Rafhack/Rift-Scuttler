package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.profile.topchampions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.RiftScuttlerApplication
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.ChampionMastery
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.Champion
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.entities.staticData.StaticData
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.loadWithGlide
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.presentation.squareUrl

class TopChampionsFragment : Fragment(), TopChampionsView {

    private lateinit var imgMastery1: ImageView
    private lateinit var imgMastery2: ImageView
    private lateinit var imgMastery3: ImageView
    private lateinit var imgChamp1: ImageView
    private lateinit var imgChamp2: ImageView
    private lateinit var imgChamp3: ImageView

    private val presenter = TopChampionsPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_champions, container, false)

        imgMastery1 = view.findViewById(R.id.fragment_top_champions_img_mastery_1)
        imgMastery2 = view.findViewById(R.id.fragment_top_champions_img_mastery_2)
        imgMastery3 = view.findViewById(R.id.fragment_top_champions_img_mastery_3)
        imgChamp1 = view.findViewById(R.id.fragment_top_img_chmp_square_1)
        imgChamp2 = view.findViewById(R.id.fragment_top_img_chmp_square_2)
        imgChamp3 = view.findViewById(R.id.fragment_top_img_chmp_square_3)

        init()
        return view
    }

    private fun init() {
        presenter.getChampsAndMasteries(RiftScuttlerApplication.summoner?.id as Long)
    }

    private fun getMasteryImage(championLevel: Int) = when (championLevel) {
        7 -> R.drawable.chmp_mast_7
        6 -> R.drawable.chmp_mast_6
        5 -> R.drawable.chmp_mast_5
        else -> R.drawable.chmp_mast_5
    }

    override fun onLoadChampAndMasteries(champs: StaticData<Champion>?, masteries: List<ChampionMastery>?) {
        val top3Masteries = masteries?.sortedBy { it.championPoints }?.reversed()?.subList(0, 3)
        val champions = champs?.data?.values
        val top3Champs = mutableListOf<Champion>()

        top3Masteries?.forEach { m ->
            top3Champs.add(champions?.find { c ->
                c?.id?.toLong() == m.championId
            } as Champion)
        }

        imgChamp2.loadWithGlide(top3Champs[0].squareUrl())
        imgChamp1.loadWithGlide(top3Champs[1].squareUrl())
        imgChamp3.loadWithGlide(top3Champs[2].squareUrl())

        imgMastery2.setImageResource(getMasteryImage(top3Masteries?.get(0)?.championLevel as Int))
        imgMastery1.setImageResource(getMasteryImage(top3Masteries[1].championLevel))
        imgMastery3.setImageResource(getMasteryImage(top3Masteries[2].championLevel))
    }

    override fun onError(message: String) {
    }

    override fun setProgress(progress: Boolean) {
    }
}