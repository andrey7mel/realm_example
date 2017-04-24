package com.andrey7mel.realm_example

import android.app.Application
import com.andrey7mel.realm_example.DI.Component
import com.andrey7mel.realm_example.DI.DaggerComponent
import io.realm.Realm
import io.realm.RealmConfiguration


class App : Application() {

    lateinit var component: Component

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        component = DaggerComponent.create()
    }

}

