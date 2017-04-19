package com.andrey7mel.realm_examlpe.view.fragments.realm_list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrey7mel.realm_examlpe.App
import com.andrey7mel.realm_examlpe.R
import com.andrey7mel.realm_examlpe.presenter.RealmResultsPresenter
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class RealmListFragment : Fragment() {


    @Inject
    lateinit var presenter: RealmResultsPresenter

    private var adapter: RealmListAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        (activity.application as App).component.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener { presenter.addObject() }
        presenter.onCreate()
        adapter = RealmListAdapter(presenter.getObjects(), presenter)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        type.text = "Type: RealmListFragment"
        empty.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}
