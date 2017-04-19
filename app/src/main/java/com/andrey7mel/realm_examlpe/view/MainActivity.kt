package com.andrey7mel.realm_examlpe.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.andrey7mel.realm_examlpe.R
import com.andrey7mel.realm_examlpe.view.fragments.SettingsFragment
import com.andrey7mel.realm_examlpe.view.fragments.realm_list.RealmListFragment
import com.andrey7mel.realm_examlpe.view.fragments.rx.RxViewImpl

class MainActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_rx -> {
                replaceFragment(RxViewImpl())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                replaceFragment(RealmListFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_test -> {
                replaceFragment(SettingsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom)

        if (savedInstanceState == null) {
            replaceFragment(RxViewImpl())
        }

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

}
