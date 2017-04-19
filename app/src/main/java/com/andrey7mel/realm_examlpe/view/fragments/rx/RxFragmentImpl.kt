package com.andrey7mel.realm_examlpe.view.fragments.rx

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrey7mel.realm_examlpe.App
import com.andrey7mel.realm_examlpe.R
import com.andrey7mel.realm_examlpe.model.DataObject
import com.andrey7mel.realm_examlpe.presenter.RxPresenter
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class RxFragmentImpl : Fragment(), RxFragment {


    @Inject
    lateinit var presenter: RxPresenter

    private var adapter: RxAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        (activity.application as App).component.inject(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener { presenter.addObject() }
        adapter = RxAdapter(presenter)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        type.text = "Type: RxFragment"

        presenter.onViewCreated(this)
    }

    override fun showData(list: List<DataObject>) {
        adapter?.list = list
        empty.visibility = View.GONE
    }

    override fun showEmpty() {
        empty.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Fix crash Realm closed error in getItemCount()
        recycler_view.adapter = null
        presenter.onDestroyView()
    }
}
