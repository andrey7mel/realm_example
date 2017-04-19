package com.andrey7mel.realm_examlpe.presenter

import com.andrey7mel.realm_examlpe.model.Model
import com.andrey7mel.realm_examlpe.view.fragments.rx.RxView
import rx.Subscription
import javax.inject.Inject

class RxPresenter @Inject
internal constructor(model: Model) : BasePresenter(model) {

    var subscription: Subscription? = null

    fun onViewCreated(rxView: RxView) {
        subscription = model.getObjectsObservable().subscribe {
            if (it.isEmpty()) {
                rxView.showEmpty()
            } else {
                rxView.showData(it)
            }
        }
    }

    fun onDestroyView() {
        subscription?.unsubscribe()
    }

}