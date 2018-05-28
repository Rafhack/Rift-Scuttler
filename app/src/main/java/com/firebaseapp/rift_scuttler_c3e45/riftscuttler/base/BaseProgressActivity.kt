package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import com.firebaseapp.rift_scuttler_c3e45.riftscuttler.R
import kotterknife.bindView

open class BaseProgressActivity : AppCompatActivity() {

    private val frmRoot by bindView<CoordinatorLayout>(R.id.activity_base_progress_frm_root)
    private val frmProgress by bindView<FrameLayout>(R.id.activity_base_progress_frm_progress)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base_progress)
    }

    override fun setContentView(layoutResID: Int) {
        val frmContent: FrameLayout = frmRoot.findViewById(R.id.activity_base_progress_frm_content)
        layoutInflater.inflate(layoutResID, frmContent, true)
        super.setContentView(frmRoot)
    }

    protected fun showProgress() {
        frmProgress.visibility = VISIBLE
    }

    protected fun hideProgress() {
        frmProgress.visibility = GONE
    }

    protected fun showStandardSnack(message: String) {
        Snackbar.make(findViewById(R.id.activity_base_progress_frm_root),
                message, Snackbar.LENGTH_SHORT).show()
    }

}