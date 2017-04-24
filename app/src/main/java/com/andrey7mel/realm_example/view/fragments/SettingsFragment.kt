package com.andrey7mel.realm_example.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andrey7mel.realm_example.App
import com.andrey7mel.realm_example.R
import com.andrey7mel.realm_example.model.Model
import io.realm.Realm
import kotlinx.android.synthetic.main.settings_fragment.*
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var model: Model

    var realm: Realm? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        (activity.application as App).component.inject(this)
        realm = Realm.getDefaultInstance()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clear_realm.setOnClickListener { model.clearRealm() }
        add_test_object.setOnClickListener { model.addTestObject() }
        get_test_object.setOnClickListener { log(model.getObject(realm!!, 0)) }
        crash_async.setOnClickListener {
            val data = model.getObjectAsync(realm!!, 0)
            try {
                log(data?.id)
            } catch(e: Exception) {
                e.printStackTrace()
                log(e.localizedMessage)
            }
        }
        get_test_object_observable.setOnClickListener { model.getObjectObservable(realm!!, 0).subscribe({ log(it) }) }
        async_exception.setOnClickListener { model.asyncTransactionException() }

    }

    private fun log(log: Any?) {
        Toast.makeText(activity, log.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        realm?.close()
    }
}
