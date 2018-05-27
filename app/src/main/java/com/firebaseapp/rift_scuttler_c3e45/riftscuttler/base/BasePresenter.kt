package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.base

import rx.Subscription

open class BasePresenter<V : BaseView>(protected var view: V) {

    protected var subscription: Subscription? = null

    fun detachView() {
        subscription?.unsubscribe()
    }

}