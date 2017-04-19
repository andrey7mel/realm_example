package com.andrey7mel.realm_examlpe.DI

import com.andrey7mel.realm_examlpe.view.fragments.SettingsFragment
import com.andrey7mel.realm_examlpe.view.fragments.realm_list.RealmListFragment
import com.andrey7mel.realm_examlpe.view.fragments.rx.RxViewImpl
import javax.inject.Singleton

@Singleton
@dagger.Component
interface Component {
    fun inject(fragment: RealmListFragment)
    fun inject(rxFragment: RxViewImpl)
    fun inject(settingsFragment: SettingsFragment)
}
