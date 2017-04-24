package com.andrey7mel.realm_example.view.fragments.realm_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andrey7mel.realm_example.R
import com.andrey7mel.realm_example.model.DataObject
import com.andrey7mel.realm_example.presenter.RealmResultsPresenter
import com.andrey7mel.realm_example.view.fragments.ViewHolder
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


class RealmListAdapter(var dataList: OrderedRealmCollection<DataObject>, val presenter: RealmResultsPresenter) : RealmRecyclerViewAdapter<DataObject, ViewHolder>(dataList, true) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), presenter)

}
