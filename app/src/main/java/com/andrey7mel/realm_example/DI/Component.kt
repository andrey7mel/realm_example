package com.andrey7mel.realm_example.DI

import com.andrey7mel.realm_example.view.fragments.SettingsFragment
import com.andrey7mel.realm_example.view.fragments.realm_list.RealmListFragment
import com.andrey7mel.realm_example.view.fragments.rx.RxViewImpl
import javax.inject.Singleton

@Singleton
@dagger.Component
interface Component {
    fun inject(fragment: RealmListFragment)
    fun inject(rxFragment: RxViewImpl)
    fun inject(settingsFragment: SettingsFragment)
}
