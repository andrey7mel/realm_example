package com.andrey7mel.realm_example.view.fragments.rx

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.andrey7mel.realm_example.R
import com.andrey7mel.realm_example.model.DataObject
import com.andrey7mel.realm_example.presenter.RxPresenter
import com.andrey7mel.realm_example.view.fragments.ViewHolder

class RxAdapter(val presenter: RxPresenter) : RecyclerView.Adapter<ViewHolder>() {

    var list: List<DataObject> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), presenter)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}