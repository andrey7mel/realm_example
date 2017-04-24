package com.andrey7mel.realm_example.presenter

import com.andrey7mel.realm_example.model.Model
import io.realm.Realm
import javax.inject.Inject

class RealmResultsPresenter @Inject
internal constructor(model: Model) : BasePresenter(model) {

    var realm: Realm? = null

    fun getObjects() = model.getObjects(realm!!)

    fun onCreate() {
        if (realm != null && !realm!!.isClosed) {
            realm?.close()
        }
        realm = Realm.getDefaultInstance()
    }

    fun onDestroy() {
        realm?.close()
    }

}