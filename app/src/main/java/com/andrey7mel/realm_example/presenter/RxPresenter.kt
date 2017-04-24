package com.andrey7mel.realm_example.presenter

import com.andrey7mel.realm_example.model.Model
import com.andrey7mel.realm_example.view.fragments.rx.RxView
import rx.Subscription
import javax.inject.Inject

class RxPresenter @Inject
internal constructor(model: Model) : BasePresenter(model) {

    var subscription: Subscription? = null

    fun onViewCreated(rxView: RxView) {
        subscription = model.getObjectsObservable().subscribe({
            if (it.isEmpty()) {
                rxView.showEmpty()
            } else {
                rxView.showData(it)
            }
        }, { it.printStackTrace() })
    }

    fun onDestroyView() {
        subscription?.unsubscribe()
        subscription = null
    }

}